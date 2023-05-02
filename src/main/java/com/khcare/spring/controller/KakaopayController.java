package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.dto.KakaoPayDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

// https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment
@RestController
@RequestMapping("/kakaopay")
public class KakaopayController {
    Logger logger = LoggerFactory.getLogger(KakaopayController.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    KakaoPayDto kakaoPayDto = new KakaoPayDto();

    /**
     * 카카오페이 결제 준비
     * @param pMap  전달받은 정보
     * @return
     */
    @PostMapping("/ready")
    @ResponseBody
    public String kakaoPayReady(@RequestBody Map<String,Object> pMap) {
        // API주소 : https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare
        logger.info("kakaoPayReady 호출");
        String resultText = "";                 // 카카오페이 로직 결과
        String user_id = "";                    // 사용자 ID
        String pay_type = "";                   // 결제 타입 (결제, 후원)
        int order_id = 0;                       // 주문 번호 (pay_no 또는 spon_no 컬럼값)

        Map<String,Object> nMap = null;

        // json 파라미터
        logger.info("pMap : " + pMap);
        String pUserId = pMap.get("user_id").toString();

        // 결제자가 회원 or 비회원 확인
        if (pUserId != null && pUserId.length() > 0) {
            logger.info("해당 사용자는 회원입니다");
            user_id = pMap.get("user_id").toString();
        } else {
            logger.info("해당 사용자는 비회원입니다");
            user_id = pMap.get("user_tel").toString();
        }

        // 결제 타입 확인
        kakaoPayDto.setPay_type(pMap.get("pay_type").toString());
        pay_type = kakaoPayDto.getPay_type();
        logger.info("결제 타입 ===> " + pay_type);

        // 주문 번호 확인
        if ("후원".equals(pay_type)) {
            kakaoPayDto.setSpon_open(pMap.get("spon_open").toString());                                  // 후원 공개 유무
            kakaoPayDto.setSpon_content(pMap.get("spon_content").toString());                            // 후원 내용
            kakaoPayDto.setSpon_pay(pMap.get("spon_pay").toString());                                // 후원 종류

            nMap = sqlSessionTemplate.selectOne("sponsorNo");
            order_id = Integer.parseInt(nMap.get("spon_no").toString());
        } else if ("결제".equals(pay_type)) {
            kakaoPayDto.setReq_text(pMap.get("req_text").toString());
            kakaoPayDto.setUser_addr(pMap.get("user_addr").toString());

            nMap = sqlSessionTemplate.selectOne("paymentNo");
            order_id = Integer.parseInt(nMap.get("pay_no").toString());
        }

        order_id += 1;
        logger.info("주문번호 : " + String.valueOf(order_id));

        kakaoPayDto.setPartner_order_id(String.valueOf(order_id));                                   // 주문번호
        kakaoPayDto.setPartner_user_id(user_id);                                                     // 회원 id
        kakaoPayDto.setItem_name(pMap.get("item_name").toString());                                  // 상품명
        kakaoPayDto.setTotal_amount(Integer.parseInt(pMap.get("total_amount").toString()));          // 상품 총액
        kakaoPayDto.setUser_tel(pMap.get("user_tel").toString());                                    // 사용자 연락처

        String approval_url = "http://localhost:7000/kakaopay/success";                              // 성공 시 redirect url
        String cancel_url = "http://localhost:7000/kakaopay/cancel";                                 // 취소 시 redirect url
        String fail_url = "http://localhost:7000/kakaopay/fail";                                     // 실패 시 redirect url

        try {
            URL kakaoUrl = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection connServer = (HttpURLConnection) kakaoUrl.openConnection();

            // .env를 읽어서 Properties에 저장
            Properties p = new Properties();
            p.load(new FileReader(".env"));

            String key = "KakaoAK " + p.getProperty("key");         // APP_ADMIN_KEY

            connServer.setRequestMethod("POST");
            connServer.setRequestProperty("Authorization", key);
            connServer.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            // 카카오서버로 접속 시도
            connServer.setDoOutput(true);

            // 송신 할 쿼리스트링
            // https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-request
            String params = "cid=TC0ONETIME&partner_order_id=" + kakaoPayDto.getPartner_order_id() + "&partner_user_id=" + kakaoPayDto.getPartner_user_id()
                    + "&item_name=" + kakaoPayDto.getItem_name() + "&quantity=1&total_amount=" + kakaoPayDto.getTotal_amount() + "&vat_amount=0&tax_free_amount=0&payment_method_type=MONEY"
                    + "&approval_url=" + approval_url + "&cancel_url=" + cancel_url + "&fail_url=" + fail_url;
            logger.info("params : " + params);

            // 카카오서버로 송신
            OutputStream outputStream = connServer.getOutputStream();
            // 데이터 송신
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            // 쿼리스트링을 바이트 형식으로 송신
            dataOutputStream.writeBytes(params);

            // 송신 할 데이터 비우기 -> 목적지로 송신
            //dataOutputStream.flush();
            // 데이터 전달 종료 (flush()메소드 후 종료)
            dataOutputStream.close();

            // 송신 후 결과
            int result = connServer.getResponseCode();

            // 카카오서버으로부터 수신
            InputStream inputStream;

            if (result == 200) {    // HTTP CODE: 송신 성공
                inputStream = connServer.getInputStream();
            } else {                // 송신 실패
                inputStream = connServer.getErrorStream();
            }

            // 결과 확인
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 수신 받은 바이트를 String으로 변환
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // 출력 후 bufferedReader는 비워진다
            // https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-response
            resultText = bufferedReader.readLine();
        } catch (MalformedURLException e) {
            logger.error(e.toString());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException(e);
        }

        logger.info("결과 : " + resultText);
        /*
          tid = 결제 고유 번호
          next_redirect_app_url = 요청한 클라이언트(Client)가 모바일 앱일 경우, 카카오톡 결제 페이지 Redirect URL
          next_redirect_mobile_url = 요청한 클라이언트가 모바일 웹일 경우, 카카오톡 결제 페이지 Redirect URL
          next_redirect_pc_url = 요청한 클라이언트가 PC 웹일 경우, 카카오톡 결제 페이지 Redirect URL
          android_app_scheme = 카카오페이 결제 화면으로 이동하는 Android 앱 스킴(Scheme)
          ios_app_scheme = 카카오페이 결제 화면으로 이동하는 iOS 앱 스킴
          created_at = 결제 준비 요청 시간
        */

        Gson g = new Gson();
        Map<String,Object> rMap = g.fromJson(resultText, Map.class);
        logger.info(rMap.toString());

        kakaoPayDto.setTid(rMap.get("tid").toString());                                                 // 결제 고유 번호
        kakaoPayDto.setNext_redirect_mobile_url(rMap.get("next_redirect_mobile_url").toString());       // 카카오톡 결제 페이지 (모바일)
        kakaoPayDto.setNext_redirect_pc_url(rMap.get("next_redirect_pc_url").toString());               // 카카오톡 결제 페이지 (PC)
        kakaoPayDto.setCreated_at(rMap.get("created_at").toString());                                   // 결제 준비 요청 시간

        // 카카오페이 결제창 호출
        return kakaoPayDto.getNext_redirect_pc_url();
    } // end of kakaoPayReady


    /**
     * 카카오페이 승인 요청
     * @param pMap  결제 승인 요청을 인증하는 토큰
     * @return
     */
    @RequestMapping("/success")
    public String kakaoPaySucces(@RequestParam Map<String, String> pMap) {
        // API주소 : https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve
        logger.info("kakaoPaySucces 호출");
        int qResult = 0;

        // 카카오서버로 부터 전달 받은 파라미터
        logger.info("pMap " + pMap);                                              // pg_token : 결제승인 요청을 인증하는 토큰
        kakaoPayDto.setPg_token(pMap.get("pg_token"));                            // 결제승인 요청을 인증하는 토큰, 쿼리스트링

        String resultText = "";

        try {
            URL kakaoUrl = new URL("https://kapi.kakao.com/v1/payment/approve");
            HttpURLConnection connServer = (HttpURLConnection) kakaoUrl.openConnection();

            // .env를 읽어서 Properties에 저장
            Properties p = new Properties();
            p.load(new FileReader(".env"));

            String key = "KakaoAK " + p.getProperty("key");     // APP_ADMIN_KEY

            connServer.setRequestMethod("POST");
            connServer.setRequestProperty("Authorization", key);
            connServer.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            // 카카오서버로 접속 시도
            connServer.setDoOutput(true);

            // 송신 할 쿼리스트링
            // https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-request
            String params = "cid=TC0ONETIME&tid=" + kakaoPayDto.getTid() + "&partner_order_id=" + kakaoPayDto.getPartner_order_id() + "&partner_user_id=" + kakaoPayDto.getPartner_user_id()
                    + "&pg_token=" + kakaoPayDto.getPg_token();
            logger.info("params : " + params);

            // 카카오서버로 송신
            OutputStream outputStream = connServer.getOutputStream();
            // 데이터 송신
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            // 쿼리스트링을 바이트 형식으로 송신
            dataOutputStream.writeBytes(params);

            // 송신 할 데이터 비우기 -> 목적지로 송신
            //dataOutputStream.flush();
            // 데이터 전달 종료 (flush()메소드 후 종료)
            dataOutputStream.close();

            // 송신 후 결과
            int result = connServer.getResponseCode();

            // 카카오서버으로부터 수신
            InputStream inputStream;

            if (result == 200) {    // HTTP CODE: 송신 성공
                inputStream = connServer.getInputStream();
            } else {                // 송신 실패
                inputStream = connServer.getErrorStream();
            }

            // 결과 확인
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 수신 받은 바이트를 String으로 변환
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // 출력 후 bufferedReader는 비워진다
            // https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-response
            resultText = bufferedReader.readLine();
        } catch (MalformedURLException e) {
            logger.error(e.toString());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException(e);
        }

        logger.info("결과 : " + resultText);
        /*
            aid = 요청 고유 번호,
            tid = 결제 고유 번호,
            cid = 가맹점 코드,
            partner_order_id = 가맹점 주문번호,
            partner_user_id = 회원 id,
            payment_method_type = 카카오페이,
            item_name = 상품명,
            quantity = 1,
            amount = 상품 수량,
            created_at = 결제 준비 요청 시각,
            approved_at = 결제 승인 시각
        */

        Gson g = new Gson();
        Map<String,Object> rMap = g.fromJson(resultText, Map.class);
        logger.info(rMap.toString());

        kakaoPayDto.setAid(rMap.get("aid").toString());                         // aid: 요청 고유 번호
        //kakaoPayDto.setApproved_at(rMap.get("approved_at").toString());         // approved_at: 결제 승인 시각

        StringBuilder sb = new StringBuilder();

        // 결제 완료한 값 DB에 저장
        String pay_type = kakaoPayDto.getPay_type();
        if ("결제".equals(pay_type)) {
            qResult = sqlSessionTemplate.insert("paymentInsertKakao", kakaoPayDto);
            logger.info("카카오페이 결제 로직 완료");

            sb.append("<script>");
            sb.append("  window.opener.parent.location=\'http://localhost:3000/payment/success\';");
            sb.append("  window.close();");
            sb.append("</script>");

            return sb.toString();
        } else if ("후원".equals(pay_type)) {
            qResult = sqlSessionTemplate.insert("sponsorInsertKakao", kakaoPayDto);
            logger.info("카카오페이 후원 로직 완료");

            sb.append("<script>");
            sb.append("  window.opener.parent.location=\'http://localhost:3000/sponsor/success\';");
            sb.append("  window.close();");
            sb.append("</script>");

            return sb.toString();
        } else {
            return "";
        }
    } // end of kakaoPaySucces


    /**
     * 카카오페이 취소 요청
     * @return
     */
    @RequestMapping("/cancel")
    public String kakaoPayCancel() {
        // API주소 : https://developers.kakao.com/docs/latest/ko/kakaopay/cancellation#결제-취소하기
        logger.info("kakaoPayCancel 호출");
        String resultText = "";

        try {
            URL kakaoUrl = new URL("https://kapi.kakao.com/v1/payment/cancel");
            HttpURLConnection connServer = (HttpURLConnection) kakaoUrl.openConnection();

            connServer.setRequestMethod("POST");
            connServer.setRequestProperty("Authorization", "KakaoAK 37852f978f6dc3768976fea78a63b045");     // APP_ADMIN_KEY
            connServer.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            // 카카오서버로 접속 시도
            connServer.setDoOutput(true);

            // 송신 할 쿼리스트링
            // https://developers.kakao.com/docs/latest/ko/kakaopay/cancellation#cancellation-request
            String tid = "test";    // 테스트용
            String params = "cid=TC0ONETIME&tid=" + tid
                    +"&cancel_amount=2200&cancel_vat_amount=200&cancel_tax_free_amount=0&cancel_available_amount=4000";

            // 카카오서버로 송신
            OutputStream outputStream = connServer.getOutputStream();
            // 데이터 송신
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            // 쿼리스트링을 바이트 형식으로 송신
            dataOutputStream.writeBytes(params);

            // 송신 할 데이터 비우기 -> 목적지로 송신
            //dataOutputStream.flush();
            // 데이터 전달 종료 (flush()메소드 후 종료)
            dataOutputStream.close();

            // 송신 후 결과
            int result = connServer.getResponseCode();

            // 카카오서버으로부터 수신
            InputStream inputStream;

            if (result == 200) {    // HTTP CODE: 송신 성공
                inputStream = connServer.getInputStream();
            } else {                // 송신 실패
                inputStream = connServer.getErrorStream();
            }

            // 결과 확인
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 수신 받은 바이트를 String으로 변환
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // 출력 후 bufferedReader는 비워진다
            // https://developers.kakao.com/docs/latest/ko/kakaopay/cancellation#cancellation-response
            resultText = bufferedReader.readLine();
        } catch (MalformedURLException e) {
            logger.error(e.toString());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException(e);
        }

        logger.info("결과 : " + resultText);

        StringBuilder sb = new StringBuilder();

        sb.append("<script>");
        sb.append("  window.close();");
        sb.append("</script>");

        return sb.toString();
    } // end of kakaoPayCancel

    /**
     * 카카오페이 결제 실패
     * @return
     */
    @RequestMapping("/fail")
    @ResponseBody
    public String kakaoPayFail() {
        logger.info("kakaoPayFail 호출");


        return null;
    } // end of kakaoPayFail
}
