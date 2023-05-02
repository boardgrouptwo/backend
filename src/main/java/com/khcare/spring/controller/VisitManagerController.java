package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.VisitManagerService;
import com.khcare.spring.Service.VisitManagerServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visitmanager")
@Log4j2

public class VisitManagerController {
    @Autowired
    private VisitManagerServiceImpl visitManagerServiceImpl;

    //면회 승인
    @PostMapping("/visitMInsert")
    public int visitMInsert(@RequestBody Map<String, Object> pMap) {
        log.info("면회관리Insert");
        log.info(pMap);
        int result = 0;
        result = visitManagerServiceImpl.visitMInsert(pMap);
        return result;
    }
    @PostMapping("/visitMUpdate")
    public int visitMUpdate(@RequestBody Map<String,Object> pMap) {
        log.info("form update");
        log.info(pMap);
        int result = 0;
        result = visitManagerServiceImpl.visitMUpdatet(pMap);
        return result;
    }
    @GetMapping("/visitMList")
    public String visitMList(@RequestParam Map<String,Object> pMap) {
        log.info("visitMList 호출");
        log.info(pMap);
        List<Map<String,Object>> bList = null;
        bList = visitManagerServiceImpl.visitMList(pMap);
        log.info(bList);
        Gson g = new Gson();
        String temp =g.toJson(bList);
        log.info(temp+"");
        return temp;
    }
    @PostMapping("/visitMDelete")
    public int visitMDelete(@RequestBody Map<String, Object> pMap){
        log.info("면회신청 삭제");
        int result = 0;
        result = visitManagerServiceImpl.visitMDelete(pMap);
        return result;
    }
}
