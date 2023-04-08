package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface ShopService {

    List<Map<String,Object>> productList(Map<String,Object> pMap);

    int productUpload(Map<String, Object> pMap);
}
