package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.ElderService;
import com.khcare.spring.Service.ElderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/elder")
public class ElderController {
    Logger log = LogManager.getLogger(ElderController.class);
    @Autowired
    ElderServiceImpl elderService;

    //어르신 가입
    @PostMapping("/elderJoin")
    public int elderJoin(@RequestBody Map<String,Object> pMap) {
        int result = 0;
        result = elderService.elderJoin(pMap);
        return result;
    }


    //유저는 가입 후 어르신 정보 후에 입력할 때
    @PostMapping("/elderInsert")
    public int elderInsert(@RequestBody Map<String,Object> pMap) {
        log.info("elderInsert 호출");
        log.info("pMap : " + pMap);
        int result = 0;

        result = elderService.elderInsert(pMap);

        return result;
    }

    //어르신 정보 수정






    //어르신 정보 출력
    @GetMapping("/elderSelect")
    public Map<String,Object> elderSelect(@RequestParam Map<String,Object> pMap) {
        log.info("elderSelect 호출");
        log.info("pMap : " + pMap);
        Map<String,Object> rMap = null;

        rMap = elderService.elderSelect(pMap);

        Gson g = new Gson();
        String result = g.toJson(rMap);
        log.info("result : " + result);

        return rMap;
    }

}
