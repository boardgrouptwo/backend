package com.khcare.spring.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//면회예약정보 저장 조회 DB연결 쿼리문
@Repository
public class VisitDao {
    Logger logger = LoggerFactory.getLogger(VisitDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    public List<Map<String, Object>> visitList(Map<String,Object> pMap) {
        logger.info(pMap+"");
        List<Map<String, Object>> pList = null;
        pList = sqlSessionTemplate.selectList("visitList", pMap);
        logger.info(pList+"");
        return pList;
    }



    public int visitInsert(Map<String, Object> pMap) {
        int result = 0;

        result = sqlSessionTemplate.insert("visitInsert", pMap);
        logger.info(result+"면회추가");
        return result;
    }

    public int visitUpdate(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.insert("visitUpdate", pMap);
        return result;
    }
}
