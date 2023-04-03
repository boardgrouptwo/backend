package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.PaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    }

    //TODO: 조회 관련 모든 메소드 파라미터 수정 필요

    /**
     * 전체 결제 내역 출력
     * @return
     */
    @GetMapping("/list")
    public String paymentList(@RequestBody Map<String,Object> pMap) {
        logger.info("paymentList 호출");
        List<Map<String,Object>> rList = null;

        rList = paymentServiceImpl.paymentList();
        Gson g = new Gson();
        String result = g.toJson(rList);
        logger.info("result : " + result);

        return result;
    }

    /**
     * 결제번호를 통한 결제 내역 조회
     * @param payNo: 결제 번호
     * @return
     */
    @GetMapping("/numlist")
    public String paymentNumList(@RequestParam int payNo) {
        logger.info("paymepaymentNumListntList 호출");
        logger.info("payNo : " + payNo);
        List<Map<String,Object>> rList = null;

        rList = paymentServiceImpl.paymentNumList(payNo);
        Gson g = new Gson();
        String result = g.toJson(rList);
        logger.info("result : " + result);

        return result;
    }

    /**
     * 특정 사용자의 결제 내역 조회
     * @param userId: 회원 ID
     * @return
     */
    @GetMapping("/userlist")
    public String paymentUserList(@RequestParam String userId) {
        logger.info("paymentUserList 호출");
        logger.info("userId : " + userId);
        List<Map<String,Object>> rList = null;

        rList = paymentServiceImpl.paymentUserList(userId);
        Gson g = new Gson();
        String result = g.toJson(rList);
        logger.info("result : " + result);

        return result;
    }

    /**
     * 특정 날짜 결제 내역 조회
     * @param pMap: 날짜 정보
     * @return
     */
    @GetMapping("/datelist")
    public String paymentDateList(@RequestBody Map<String,Object> pMap) {
        logger.info("paymentDateList 호출");
        logger.info("pMap : " + pMap);
        List<Map<String,Object>> rList = null;

        rList = paymentServiceImpl.paymentDateList(pMap);
        Gson g = new Gson();
        String result = g.toJson(rList);
        logger.info("result : " + result);

        return result;
    }

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
    }
}
