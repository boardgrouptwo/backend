package com.khcare.spring.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class ServiceDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int serviceInsert(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("serviceInsert", pMap);
        return result;
    }

    public List<Map<String, Object>> managerList(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("managerList", pMap);
        return bList;
    }
}
