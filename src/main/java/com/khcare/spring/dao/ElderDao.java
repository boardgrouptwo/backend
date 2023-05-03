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
    SqlSessionTemplate sqlSessionTemplate = null;

    //어르신 가입
    public int elderJoin(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("elderJoin", pMap);

        return result;
    }


    //어르신 추가
    public int elderInsert(Map<String, Object> pMap) {
        log.info("elderInsert 호출");
        int result = 0;

        result = sqlSessionTemplate.update("elderInsert", pMap);
        log.info("결과 : " + result);

        return result;
    }

    //어르신 정보 출력
    public Map<String,Object> elderSelect(Map<String, Object> pMap) {
        log.info("elderSelect 호출");
        Map<String,Object> rMap = null;

        rMap = sqlSessionTemplate.selectOne("elderSelect", pMap);
        log.info("결과 : " + rMap);

        return rMap;
    }


    //어르신 정보 수정
    public int elderUpdate(Map<String,Object> pMap) {
        log.info("elderUpdate 호출");
        log.info(pMap);
        log.info("내원자 이름 : " + pMap.get("elder_name"));
        int result = 0;

        result = sqlSessionTemplate.update("elderUpdate", pMap);

        return result;
    }
}
