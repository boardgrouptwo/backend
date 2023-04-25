package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.ElderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/elder")
public class ElderController {
    Logger log = LogManager.getLogger(ElderController.class);
    @Autowired
    private ElderServiceImpl elderService = null;

    @PostMapping("/elderInsert")
    public int elderInsert(@RequestParam Map<String,Object> pMap) {
        log.info("elderInsert 호출");
        log.info("pMap : " + pMap);
        int result = 0;

        result = elderService.elderInsert(pMap);

        return result;
    }

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
