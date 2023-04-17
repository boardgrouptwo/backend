package com.khcare.spring.controller;

import com.khcare.spring.Service.KhServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/service")
@Log4j2
public class KhServiceController {
    @Autowired
    private KhServiceImpl khServiceImpl;

    @PostMapping("/insert")
    public int noticeInsert(@RequestBody Map<String,Object> pMap) {
        log.info("form insert");
        log.info(pMap);
        int result = 0;
        result = khServiceImpl.serviceInsert(pMap);
        return result;
    }

}


