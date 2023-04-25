package com.khcare.spring.Service;

import com.khcare.spring.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDao shopDao;

    @Override
    public List<Map<String, Object>> productList(Map<String, Object> pMap) {
        List<Map<String, Object>> pList = null;
        pList = shopDao.productList(pMap);
        return pList;
    }

    @Override
    public int productUpload(Map<String, Object> pMap) {
        int result = 0;
        result = shopDao.productUpload(pMap);
        return 0;
    }

    @Override
    public int productHit(Map<String, Object> pMap) {
        int result = 0;
        result = shopDao.productHit(pMap);
        return 0;
    }

    @Override
    public List<Map<String, Object>> productReceiveList(Map<String, Object> pMap) {
        List<Map<String, Object>> pList = null;
        pList = shopDao.productReceiveList(pMap);
        return pList;
    }
}
