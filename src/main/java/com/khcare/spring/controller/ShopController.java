package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.ShopService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;
    @Autowired
    private ServletContext servletContext;
    Logger logger = LoggerFactory.getLogger(ShopController.class);

    @PostMapping("/imageUpload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        // 경로는 해당 pc에 맞게 바꿔야함
        //String filePath = "D://final_project/backend/src/main/resources/static/images/shop/" + fileName;
        String filePath = "D://final_project/frontend/public/images/shop" + "/" + fileName;
        //String filePath = "images/shop/" + fileName;
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

    @GetMapping("/hitAdd")
    public int productHit(@RequestParam Map<String, Object> pMap) {
        logger.info(pMap+"");
        int result=0;
        result = shopService.productHit(pMap);
        return result;
    }


    @GetMapping("/productReceiveList")
    public String productReceiveList(@RequestParam Map<String,Object> pMap) {
        logger.info(pMap+"");
        List<Map<String,Object>> pList = null;
        pList = shopService.productReceiveList(pMap);
        Gson g = new Gson();
        String temp =g.toJson(pList);
        return temp;
    }
}
