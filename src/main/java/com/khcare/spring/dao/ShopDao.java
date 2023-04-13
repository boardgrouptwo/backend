package com.khcare.spring.dao;

import com.khcare.spring.controller.KakaopayController;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ShopDao {

    Logger logger = LoggerFactory.getLogger(ShopDao.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> productList(Map<String,Object> pMap) {
        logger.info(pMap+"");
        List<Map<String, Object>> pList = null;
        pList = sqlSessionTemplate.selectList("productList", pMap);
        logger.info(pList+"");
        return pList;
    }

    public int productUpload(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("productUpload", pMap);
        logger.info(result+"");
        return result;
    }

    public int productHit(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("productHit", pMap);
        logger.info(result+"");
        return result;
    }
}
