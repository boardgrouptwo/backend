package com.khcare.spring.controller;

import com.khcare.spring.Service.SponsorServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/spon")
@Log4j2
public class SponsorController {
    @Autowired
    private SponsorServiceImpl sponsorServiceImpl;

    @PostMapping("/insert")
    public int sponsorFormInsert(@RequestBody Map<String,Object> pMap) {
        log.info("form insert");
        log.info(pMap);
        int result = 0;
        result = sponsorServiceImpl.sponsorFormInsert(pMap);
        return result;
    }

}
