package com.khcare.spring.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ElderDao {
    Logger log = LogManager.getLogger(ElderDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;

    public int elderInsert(Map<String, Object> pMap) {
        log.info("elderInsert 호출");
        int result = 0;

        result = sqlSessionTemplate.insert("elderInsert", pMap);
        log.info("결과 : " + result);

        return result;
    }

    public Map<String,Object> elderSelect(Map<String, Object> pMap) {
        log.info("elderSelect 호출");
        Map<String,Object> rMap = null;

        rMap = sqlSessionTemplate.selectOne("elderSelect", pMap);
        log.info("결과 : " + rMap);

        return rMap;
    }



    public int elderUpdate(Map<String,Object> pMap) {
        log.info("elderUpdate 호출");
        log.info(pMap);
        log.info("내원자 이름 : " + pMap.get("elder_name"));
        int result = 0;

        result = sqlSessionTemplate.update("elderUpdate", pMap);

        return result;
    }
}
