package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.VisitServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visit/sign")
public class VisitController {
    Logger logger = LoggerFactory.getLogger(VisitController.class);
@Autowired
private VisitServiceImpl visitServiceImpl;
    //예약목록
    @GetMapping("/visitList")
    public String visitList(@RequestParam Map<String,Object> pMap) {
        logger.info(pMap+"면회 리스트 호출(관리자)");
        List<Map<String,Object>> bList = null;
        bList = visitServiceImpl.visitList(pMap);
        Gson g = new Gson();
        String temp =g.toJson(bList);
        return temp;
    }
    //예약추가
    @PostMapping("/insert")
    public int visitInsert(@RequestBody Map<String,Object>pMap){
        logger.info("면회추가");
        int result = 0;
        result = visitServiceImpl.visitInsert(pMap);
        logger.info(result + "");
        logger.info(pMap + "");
        return result;
    }

    //예약 수정
    @PostMapping("/update")
    public int visitUpdate(@RequestBody Map<String, Object>pMap) {
        logger.info("면회 수정");
        int result = 0;
        result = visitServiceImpl.visitUpdate(pMap);
        return result;

    }

    //예약취소
    @GetMapping("delete")
    public int visitDelete(@RequestParam Map<String, Object>pMap) {
        logger.info("면회 취소");
        int result =9;
        result = visitServiceImpl.visitDelete(pMap);
        return result;
    }

}
