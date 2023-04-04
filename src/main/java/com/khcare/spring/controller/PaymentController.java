package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.PaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    /**
     * 결제 내역 추가
     * @param pMap: 결제 정보
     * @return 0: 실패 / 1: 성공
     */
    @PostMapping("/Insert")
    public int paymentInsert(@RequestBody Map<String,Object> pMap) {
        logger.info("paymentInsert 호출");
        logger.info("pMap : " + pMap);
        int result = 0;

        result = paymentServiceImpl.paymentInsert(pMap);
        logger.info("result : " + result);

        return result;
    } // end of paymentInsert


    /**
     * 전체 결제 내역 출력
     * @return
     */
    @GetMapping("/list")
    public String paymentList(@RequestBody Map<String,Object> pMap) {
        logger.info("paymentList 호출");
        logger.info("pMap : " + pMap);
        List<Map<String,Object>> rList = null;

        if (pMap.get("condition") != null) {
            logger.info("검색 분류 ===> " + pMap.get("condition"));
        }

        rList = paymentServiceImpl.paymentList();

        Gson g = new Gson();
        String result = g.toJson(rList);
        logger.info("result : " + result);

        return result;
    } // end of paymentList


    /**
     * 결제 환불
     * @param payNo: 결제 번호
     * @return 0: 실패 / 1: 성공
     */
    @GetMapping("/delete")
    public int paymentDelete(@RequestParam int payNo) {
        logger.info("paymentDelete 호출");
        logger.info("payNo : " + payNo);
        int result = 0;

        result = paymentServiceImpl.paymentDelete(payNo);
        logger.info("result : " + result);

        return result;
    } // end of paymentDelete


    // https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment

    /**
     * 카카오페이 결제 준비
     * @param pMap  전달받은 정보
     * @return
     */
    @RequestMapping("/kakaopay")
    @ResponseBody
    public String kakaoPayReady(@RequestBody Map<String,Object> pMap) {
        // API주소 : https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare
        logger.info("kakaoPayReady 호출");
        logger.info("pMap : " + pMap);
        String resultText = "";

        String userID = pMap.get("user_id").toString();                               // 가맹점 회원 id
        String order_id = pMap.get("order_id").toString();                            // 주문번호
        String item_name = pMap.get("item_name").toString();                          // 회원 id
        int total_amount = Integer.parseInt(pMap.get("total_amount").toString());     // 상품 총액
        String approval_url = "https://localhost:8000/kakaopay/success";              // 성공 시 redirect url
        String cancel_url = "https://localhost:8000/kakaopay/cancel";                 // 취소 시 redirect url
        String fail_url = "https://localhost:8000/kakaopay/fail";                     // 실패 시 redirect url

        try {
            URL kakaoUrl = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection connServer = (HttpURLConnection) kakaoUrl.openConnection();

            connServer.setRequestMethod("POST");
            connServer.setRequestProperty("Authorization", "KakaoAK 37852f978f6dc3768976fea78a63b045");     // APP_ADMIN_KEY
            connServer.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            // 카카오서버로 접속 시도
            connServer.setDoOutput(true);

            // 송신 할 쿼리스트링
            // https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-request
            String params = "cid=TC0ONETIME&partner_order_id=" + order_id + "&partner_user_id=" + userID
                    + "&item_name=" + item_name + "&quantity=1&total_amount=" + total_amount + "&vat_amount=0&tax_free_amount=0&payment_method_type=MONEY"
                    + "&approval_url=" + approval_url + "&cancel_url=" + cancel_url + "&fail_url=" + fail_url;

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

        return resultText;
    } // end of kakaoPayReady

    /**
     * 카카오페이 승인 요청
     * @param pg_token  결제 승인 요청을 인증하는 토큰
     * @return
     */
    @RequestMapping("/kakaopay/success")
    @ResponseBody
    public String kakaoPaySucces(@RequestParam String pg_token) {
        // API주소 : https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve
        logger.info("kakaoPaySucces 호출");
        logger.info("pg_token: " + pg_token);
        String resultText = "";

        try {
            URL kakaoUrl = new URL("https://kapi.kakao.com/v1/payment/approve");
            HttpURLConnection connServer = (HttpURLConnection) kakaoUrl.openConnection();

            connServer.setRequestMethod("POST");
            connServer.setRequestProperty("Authorization", "KakaoAK 37852f978f6dc3768976fea78a63b045");     // APP_ADMIN_KEY
            connServer.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            // 카카오서버로 접속 시도
            connServer.setDoOutput(true);

            // 송신 할 쿼리스트링
            // https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-request
            String tid = "test";    // 테스트용
            String params = "cid=TC0ONETIME&tid=" + tid + "partner_order_id=partner_order_id&partner_user_id=partner_user_id"
                    + "&pg_token=" + pg_token;

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

        return "/";
    } // end of kakaoPaySucces

    /**
     * 카카오페이 취소 요청
     * @return
     */
    @RequestMapping("/kakaopay/cancel")
    @ResponseBody
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

        return "/";
    } // end of kakaoPayCancel

    /**
     * 카카오페이 결제 실패
     * @return
     */
    @RequestMapping("/kakaopay/fail")
    @ResponseBody
    public String kakaoPayFail() {
        logger.info("kakaoPayFail 호출");


        return null;
    } // end of kakaoPayFail
}
