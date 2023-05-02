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

        List<Map<String, Object>> bList = null;
        bList = qnaServiceImpl.qnaList(pMap);

        Gson g = new Gson();
        String temp = g.toJson(bList);

        return temp;
    }

    @PostMapping("/qnaInsert")
    @CrossOrigin(origins = "http://localhost:3000")
    public int qnaInsert(@RequestBody Map<String, Object> pMap){

        int result = 0;
        result = qnaServiceImpl.qnaInsert(pMap);

        return result;
    }

    @PostMapping("/qnaUpdate")
    public int qnaUpdate(@RequestBody Map<String, Object> pMap){
        int result = 0;
        result = qnaServiceImpl.qnaUpdate(pMap);

        return result;
    }

    @PostMapping("/repleInsert")
    public int repleInsert(@RequestBody Map<String, Object> pMap){
        int result = 0;
        result = qnaServiceImpl.repleInsert(pMap);

        return result;
    }

    @GetMapping("/qnaDelete")
    public int qnaDelete(@RequestParam Map<String, Object> pMap){
        int result = 0;
        result = qnaServiceImpl.qnaDelete(pMap);

        return result;
    }


    @GetMapping("/qnaSearch")
    public String qnaSearchList(@RequestParam Map<String, Object> pMap){
        List<Map<String,Object>> bList = null;
        bList = qnaServiceImpl.qnaSearchList(pMap);

        Gson g = new Gson();
        String temp = g.toJson(bList);

        return temp;
    }

    @GetMapping("/qnaAfterBefore")
    public String qnaAfterBefore(@RequestParam Map<String,Object> pMap){
        List<Map<String,Object>> bList = null;
        bList = qnaServiceImpl.qnaAfterBefore(pMap);

        Gson g = new Gson();
        String temp = g.toJson(bList);

        return temp;
    }


}//end of class
