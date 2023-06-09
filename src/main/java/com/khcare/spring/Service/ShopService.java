package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface ShopService {

    List<Map<String,Object>> productList(Map<String,Object> pMap);

    int productUpload(Map<String, Object> pMap);

    int productHit(Map<String, Object> pMap);

    List<Map<String, Object>>productReceiveList(Map<String,Object>pMap);
    //장바구니 조회
    List<Map<String,Object>> cartList(Map<String, Object> pMap);

    // 장바구니 추가
    int addCart(Map<String,Object> pMap);


    //장바구니 삭제
    int deleteCart(Map<String, Object> pMap);

    int updateCart(Map<String, Object> pMap);

}
