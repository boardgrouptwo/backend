package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.MealServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    private MealServiceImpl mealServiceImpl;

        @GetMapping("/mealList")
        public String mealList(@RequestParam Map<String,Object> pMap){

            List<Map<String,Object>> bList = null;
            bList = mealServiceImpl.mealList(pMap);

            Gson g = new Gson();
            String temp;
            temp = g.toJson(bList);

            return temp;
        }





}//end of class
