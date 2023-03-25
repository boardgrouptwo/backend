package com.khcare.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class BoardController {

    @GetMapping("boardList")
    public String boardList() {

        List<Map<String,Object>> bList = null;
        String temp = null;
        return temp;
    }
}
