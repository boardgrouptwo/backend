package com.khcare.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TestDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> testList(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("testList", pMap);
        return bList;
    }
}
