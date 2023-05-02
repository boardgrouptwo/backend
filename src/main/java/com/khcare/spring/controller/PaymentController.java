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
     * 5건 결제 내역 출력
     * @return
     */
    @GetMapping("/listpreview")
    public String paymentListPre(@RequestParam Map<String,Object> pMap) {
        logger.info("paymentListPre 호출");
        logger.info("pMap : " + pMap);
        List<Map<String,Object>> rList = null;

        rList = paymentServiceImpl.paymentListPre(pMap);

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


    @PostMapping("/imp")
    @ResponseBody
    public String paymentImp(@RequestBody Map<String,Object> pMap) {
        logger.info("paymentImp 호출(아임포트)");
        logger.info(pMap.toString());
        String result = null;

        result = paymentServiceImpl.paymentImp(pMap);

        return result;
    }
}
