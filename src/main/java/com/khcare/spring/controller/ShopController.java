package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    Logger logger = LoggerFactory.getLogger(ShopController.class);

    @PostMapping("/imageUpload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "D://project/final/frontend/public/images/shop" + "/" + fileName;
        logger.info(fileName);
        logger.info(filePath);
        File dest = new File(filePath);
        file.transferTo(dest);
        return fileName;
    }

    @GetMapping("/productList")
    public String productList(@RequestParam Map<String,Object> pMap) {
        logger.info(pMap+"");
        List<Map<String,Object>> pList = null;
        pList = shopService.productList(pMap);
        Gson g = new Gson();
        String temp =g.toJson(pList);
        return temp;
    }


    @PostMapping("/productUpload")
    public int productUpload(@RequestBody Map<String,Object> pMap) {
        logger.info(pMap+"");
        int result = 0;
        result = shopService.productUpload(pMap);
        return result;
    }
}
