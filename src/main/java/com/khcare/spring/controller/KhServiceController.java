package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.KhServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/update")
    public int serviceUpdate(@RequestBody Map<String,Object> pMap) {
        log.info("form update");
        log.info(pMap);
        int result = 0;
        result = khServiceImpl.serviceUpdate(pMap);
        return result;
    }

    @GetMapping("/managerList")
    public String managerList(@RequestParam Map<String,Object> pMap) {
        log.info("service manager 리스트 호출");
        log.info(pMap);
        List<Map<String,Object>> bList = null;
        bList = khServiceImpl.managerList(pMap);
        log.info(bList);
        Gson g = new Gson();
        String temp =g.toJson(bList);
        return temp;
    }

    @PostMapping("/delete")
    public int serviceDelete(@RequestBody Map<String, Object> pMap){
        log.info("봉사활동 삭제");
        int result = 0;
        result = khServiceImpl.serviceDelete(pMap);
        return result;
    }


    @GetMapping("/userDate")
    public Map<String,Object> userDate(@RequestParam Map<String,Object> pMap) {
        log.info("userDate 호출");
        log.info("pMap : " + pMap);
        Map<String,Object> rMap = null;

        rMap = khServiceImpl.userDate(pMap);

        Gson g = new Gson();
        String result = g.toJson(rMap);
        log.info("result : " + result);

        return rMap;
    }
}


