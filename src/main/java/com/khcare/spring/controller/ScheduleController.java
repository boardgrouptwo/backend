package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.ScheduleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class ScheduleController {
    Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;
    //일정 전체 조회
    @GetMapping("/scheduleList")
    public String scheduleList(@RequestParam Map<String, Object> pMap) {
        logger.info(pMap + "일정리스트 호출(관리자)");
        List<Map<String, Object>> bList = null;
        bList = scheduleServiceImpl.scheduleList(pMap);
        Gson g = new Gson();
        String temp =g.toJson(bList);
        return temp;
    }
    //일정 추가
    @PostMapping("/scheduleInsert")
    public int scheduleInsert(@RequestBody Map<String,Object>pMap){
        logger.info("일정 추가");
        int result = 0;
        result = scheduleServiceImpl.scheduleInsert(pMap);
        return result;
    }
    //예약 수정
    @PostMapping("/scheduleUpdate")
    public int scheduleUpdate(@RequestBody Map<String, Object>pMap) {
        logger.info("일정 수정");
        int result = 0;
        result = scheduleServiceImpl.scheduleUpdate(pMap);
        return result;

    }
    //예약취소메서드
    @GetMapping("scheduleDelete")
    public int scheduleDelete(@RequestParam Map<String, Object>pMap) {
        logger.info("일정 삭제");
        int result =9;
        result = scheduleServiceImpl.scheduleDelete(pMap);
        return result;
    }
    @GetMapping("scheduleSearch")
    public int scheduleSearch(@RequestParam Map<String, Object>pMap){
        logger.info("일정 검색");
        int result =9;
        result = scheduleServiceImpl.scheduleSearch(pMap);
        return result;
    }
}
