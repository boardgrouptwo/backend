package com.khcare.spring.controller;

import com.google.gson.Gson;
import com.khcare.spring.Service.ShopServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ShopServiceImpl ShopService;

    Logger logger = LoggerFactory.getLogger(CartController.class);

    // 장바구니 불러오기
    @PostMapping("/get")
    public String cartList(@RequestBody Map<String, Object> pMap) {
        logger.info(pMap+"");
        List<Map<String,Object>> cartList = null;
        cartList = ShopService.cartList(pMap);
        Gson g = new Gson();
        String temp =g.toJson(cartList);
        return temp;
    }

    @PostMapping("/add")
    public int addCartItem(@RequestBody Map<String, Object> pMap) {
        logger.info(pMap+"");
        int result =0;
        result = ShopService.addCart(pMap);
        return result;
    }
    @PostMapping("/delete")
    public int deleteCartItem(@RequestBody Map<String, Object> pMap) {
        logger.info(pMap+"");
        int result =0;
        result = ShopService.deleteCart(pMap);
        return result;
    }
    @PostMapping("/update")
    public int updateCartItem(@RequestBody Map<String, Object> pMap) {
        logger.info(pMap+"");
        int result =0;
        result = ShopService.updateCart(pMap);
        return result;
    }
}




