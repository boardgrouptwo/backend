package com.khcare.spring.controller;

import com.khcare.spring.Service.TestLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestLogic testLogic;

    @GetMapping("/list")
    public String list (@RequestParam Map<String,Object> pMap) {
        Logger logger = LoggerFactory.getLogger(TestController.class);
        List<Map<String,Object>> bList = null;
        bList = testLogic.testList(pMap);
        Gson g = new Gson();
        String temp =g.toJson(bList);
        logger.info(temp);
        return temp;
    }

}
