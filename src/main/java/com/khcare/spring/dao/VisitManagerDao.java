package com.khcare.spring.dao;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
@Log4j2
public class VisitManagerDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int visitMInsert(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("visitMInsert", pMap);
        return result;
    }

    public int visitMUpdate(Map<String, Object> pMap) {
        int result = 0;
        log.info(pMap.get("visit_no").toString());
        result = sqlSessionTemplate.update("visitMUpdate", pMap);
        result = sqlSessionTemplate.update("visitMUpdate2", pMap);
        log.info(result);
        return result;
    }

    public List<Map<String, Object>> visitMList(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("visitMList", pMap);
        log.info("visitMList" + bList);
        return bList;
    }

    public int visitMDelete(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("visitMDelete", pMap);
        return result;
    }
}
