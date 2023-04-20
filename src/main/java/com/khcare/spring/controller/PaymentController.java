package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.PaymentServiceImpl;
import com.khcare.spring.dao.PaymentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentServiceImpl paymentServiceImpl;
    @Autowired
    private PaymentDao paymentDao;

    /**
     * 결제 내역 추가
     * @param pMap: 결제 정보
     * @return 0: 실패 / 1: 성공
     */
    @PostMapping("/insert")
    public int paymentInsert(@RequestBody Map<String,Object> pMap) {
        logger.info("paymentInsert 호출");
        logger.info("pMap : " + pMap);
        int result = 0;

        /*
        *         // 회원번호 컬럼을 int타입으로 변경하지 않으면 부적합 열유형 111에러메시지 - 다 이 문제
        // Map, List : Object 주의할 것 - 부적합한 열유형 setNull(111)
        if (pMap.get("mem_no") != null) {
            // NumberFormatException 원인이 된다
            int mem_no = Integer.parseInt(pMap.get("mem_no").toString());
            pMap.put("mem_no", mem_no);
        }*/

        result = paymentServiceImpl.paymentInsert(pMap);
        logger.info("result : " + result);

        return result;
    } // end of paymentInsert


    /**
     * 전체 결제 내역 출력
     * @return
     */
    @GetMapping("/list")
    public String paymentList(@RequestParam Map<String,Object> pMap) {
        logger.info("paymentList 호출");
        logger.info("pMap : " + pMap);
        List<Map<String,Object>> rList = null;

        rList = paymentServiceImpl.paymentList(pMap);

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

    /**
     * 결제 테이블 pay_no값 확인
     * @return pay_no값
     */
    @GetMapping("/paymentNo")
    public int paymentNo() {
        logger.info("paymentNo 호출");
        int result = 0;

        System.out.println(paymentServiceImpl);
        result = paymentServiceImpl.paymentNo();
        logger.info("result : " + result);

        return result;
    } // end of paymentNo
}
