package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.QnAServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/qna")
@Log4j2
public class QnAController {

    @Autowired
    private QnAServiceImpl qnaServiceImpl;

    @GetMapping("/qnaList")
    public String qnaList(@RequestParam Map<String, Object> pMap) {
        log.info("qna 게시판 리스트 호출");
        log.info(pMap);

        List<Map<String, Object>> bList = null;
        bList = qnaServiceImpl.qnaList(pMap);
        log.info(bList);

        Gson g = new Gson();
        String temp = g.toJson(bList);

        return temp;
    }





}//end of class
