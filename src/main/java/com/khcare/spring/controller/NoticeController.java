package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.NoticeServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
@Log4j2
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeServiceImpl;

    @GetMapping("/boardList")
    public String boardList(@RequestParam Map<String,Object> pMap) {
        log.info("notice 게시판 리스트 호출");
        List<Map<String,Object>> bList = null;
        bList = noticeServiceImpl.noticeList(pMap);
        log.info(bList);
        Gson g = new Gson();
        String temp =g.toJson(bList);
        return temp;
    }
}