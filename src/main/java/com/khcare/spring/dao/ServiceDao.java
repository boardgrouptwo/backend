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

    public int serviceUpdate(Map<String, Object> pMap) {
        int result = 0;
        log.info(pMap.get("service_no").toString());
        result = sqlSessionTemplate.update("serviceUpdate", pMap);
        result = sqlSessionTemplate.update("serviceUpdate2", pMap);
        log.info(result);
        return result;
    }

    public int serviceDelete(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("serviceDelete", pMap);
        return result;
    }

    public Map<String, Object> userDate(Map<String, Object> pMap) {
        log.info("elderSelect 호출");
        Map<String,Object> rMap = null;

        rMap = sqlSessionTemplate.selectOne("userDate", pMap);
        log.info("결과 : " + rMap);

        return rMap;
    }

    public int reviewInsert(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("reviewInsert", pMap);
        return result;
    }
}
