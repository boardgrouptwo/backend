package com.khcare.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    Logger log = LoggerFactory.getLogger(UserDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;

    public Map<String, Object> userInfo(Map<String, Object> pMap) {
        log.info("userInfo 호출");
        Map<String, Object> rMap = new HashMap<String, Object>();
        String userId = pMap.get("user_id").toString();

        rMap = sqlSessionTemplate.selectMap("userInfo", userId);

        log.info("쿼리결과 : " + rMap.toString());

        return rMap;
    }
}
