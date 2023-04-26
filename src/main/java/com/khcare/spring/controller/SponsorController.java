package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.SponsorServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spon")
@Log4j2
public class SponsorController {
    Logger logger = LoggerFactory.getLogger(SponsorController.class);

    @Autowired
    private SponsorServiceImpl sponsorServiceImpl;

    @PostMapping("/insert")
    public int sponsorFormInsert(@RequestBody Map<String,Object> pMap) {
        log.info("spon form insert");
        log.info(pMap);
        int result = 0;
        result = sponsorServiceImpl.sponsorFormInsert(pMap);
        return result;
    }

    @GetMapping("/boardList")
    public String boardList(@RequestParam Map<String,Object> pMap) {
        log.info(" spon form list 호출");
        log.info(pMap);
        List<Map<String,Object>> bList = null;
        bList = sponsorServiceImpl.sponsorList(pMap);
        log.info(bList);
        Gson g = new Gson();
        String temp =g.toJson(bList);
        return temp;
    }

    public int sponsorNo() {
        logger.info("sponsorNo 호출");
        int result = 0;

        result = sponsorServiceImpl.sponsorNo();
        logger.info("result : " + result);

        return result;
    } // end of sponsorNo

    /**
     * 사용자 총 후원금액 조회
     * @param pMap 사용자 정보
     * @return 총 후원금액
     */
    @GetMapping("/sponsorUserSum")
    public int sponsorUserSum(@RequestParam Map<String,Object> pMap) {
        logger.info("sponsorUserSum 호출");
        logger.info("pMap : " + pMap);
        int result = 0;

        result = sponsorServiceImpl.sponsorUserSum(pMap);
        logger.info("result : " + result);

        return result;
    } // end of sponsorUserSum

    /**
     * 후원 통계 조회
     * @param pMap 검색 조건
     * @return 후원 통계
     */
    @GetMapping("/sponStatistic")
    public String sponStatistic(@RequestParam Map<String,Object> pMap) {
        log.info("sponStatistic 호출");
        log.info("pMap : " + pMap);
        List<Map<String,Object>> rList = null;

        rList = sponsorServiceImpl.sponStatistic(pMap);

        Gson g = new Gson();
        String result =g.toJson(rList);
        logger.info("result : " + result);

        return result;
    } // end of sponStatistic
}
