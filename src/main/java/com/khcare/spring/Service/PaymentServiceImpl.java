package com.khcare.spring.Service;

import com.khcare.spring.dao.PaymentDao;
import com.khcare.spring.dto.PayImpDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{
    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    PayImpDto payImpDto = null;

    @Override
    public int paymentInsert(Map<String, Object> pMap) {
        logger.info("paymentInsert 호출");
        int result = 0;

        result = paymentDao.paymentInsert(pMap);

        return result;
    }

    @Override
    public List<Map<String, Object>> paymentList(Map<String, Object> pMap) {
        logger.info("paymentList 호출");
        List<Map<String, Object>> rList = null;

        rList = paymentDao.paymentList(pMap);

        return rList;
    }

    @Override
    public List<Map<String, Object>> paymentListPre(Map<String, Object> pMap) {
        logger.info("paymentListPre 호출");
        List<Map<String, Object>> rList = null;

        rList = paymentDao.paymentListPre(pMap);

        return rList;
    }

    @Override
    public int paymentDelete(int payNo) {
        logger.info("paymentDelete 호출");
        int result = 0;

        result = paymentDao.paymentDelete(payNo);

        return result;
    }

    @Override
    public int paymentNo() {
        logger.info("paymentNo 호출");
        int result = 0;

        result = paymentDao.paymentNo();

        return result;
    }

    @Override
    public String paymentImp(Map<String, Object> pMap) {
        logger.info("paymentImp 호출");
        String resultText = "";                 // 카카오페이 로직 결과

        String userId = "";                     // 사용자 ID
        String payType = "";                    // 결제 타입 (결제, 후원)
        //String orderId = "";                    // 주문번호
        int orderId = 0;                    // 주문번호
        Map<String,Object> nMap = null;

        // 현재 시간 호출
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd_HHmmss");
        String dateTime = now.format(formatter);
        logger.info(dateTime);                  // 230501_132307


        // 결제자가 회원 or 비회원 확인
        String pUserId = pMap.get("user_id").toString();

        if (pUserId != null && pUserId.length() > 0) {
            logger.info("해당 사용자는 회원입니다");
            userId = pMap.get("user_id").toString();
        } else {
            logger.info("해당 사용자는 비회원입니다");
            userId = pMap.get("user_tel").toString();
        }

        // 결제 타입 확인
        payImpDto.setPayType(pMap.get("pay_type").toString());
        payType = payImpDto.getPayType();
        logger.info("결제 타입 ===> " + payType);

        // 주문 번호 확인
        if ("후원".equals(payType)) {
            payImpDto.setSponOpen(pMap.get("spon_open").toString());                                  // 후원 공개 유무
            payImpDto.setSponContent(pMap.get("spon_content").toString());                            // 후원 내용
            payImpDto.setSponPay(pMap.get("spon_pay").toString());                                // 후원 종류

            //orderId = "SPON_" + dateTime;
            orderId = Integer.parseInt(nMap.get("spon_no").toString());
        } else if ("결제".equals(payType)) {
            payImpDto.setReqText(pMap.get("req_text").toString());
            payImpDto.setUserAddr(pMap.get("user_addr").toString());

            //orderId = "PAY_" + dateTime;
            orderId = Integer.parseInt(nMap.get("pay_no").toString());
        }
        orderId += 1;
        logger.info("주문번호 : " + String.valueOf(orderId));

        payImpDto.setPartnerOrderId(String.valueOf(orderId));                                   // 주문번호
        payImpDto.setPartnerUserId(userId);                                                     // 회원 id
        payImpDto.setItemName(pMap.get("item_name").toString());                                  // 상품명
        payImpDto.setTotalAmount(Integer.parseInt(pMap.get("total_amount").toString()));          // 상품 총액
        payImpDto.setUserTel(pMap.get("user_tel").toString());

        try {
            URL impUrl = new URL("https://api.iamport.kr/users/getToken");
            HttpURLConnection connServer = (HttpURLConnection) impUrl.openConnection();

            connServer.setRequestMethod("POST");
            //connServer.setRequestProperty("Authorization", "KakaoAK 37852f978f6dc3768976fea78a63b045");     // APP_ADMIN_KEY
            connServer.setRequestProperty("imp_key", "2503116471316663");
            connServer.setRequestProperty("imp_secret", "3YjI6x5NHlmqeZcMXFf14JlkEykkzZqsrVAYu23CbFTHbgGt3xKfkdubBDdadgceNSknklLVg3RuHr9g");
            connServer.setRequestProperty("Content-type", "application/json");
            connServer.setRequestProperty("Accept", "application/json");
            // 아임포트 서버로 접속 시도
            connServer.setDoOutput(true);

            // 송신 할 쿼리스트링
            String params = "";
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

        return null;
    }
}
