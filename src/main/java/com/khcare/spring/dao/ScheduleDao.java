package com.khcare.spring.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
@Repository
@Log4j2
public class ScheduleDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;



    public List<Map<String, Object>> scheduleList(Map<String,Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("scheduleList", pMap);
         log.info("scheduleList 호출"+bList);
        return bList;
    }

    public int scheduleUpdate(Map<String, Object> pMap) {
        int result = 0;
        log.info(pMap);
        result = sqlSessionTemplate.update("scheduleUpdate", pMap);
        return result;
    }
    public int scheduleDelete(Map<String, Object>pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("scheduleDelete", pMap);
        return result;
    }
    public int scheduleInsert(Map<String, Object>pMap) {
        int result = 0;
        result = sqlSessionTemplate.insert("scheduleInsert", pMap);
        log.info("스케줄Dao" + pMap);
        return result;
    }

    public int scheduleSearch(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.insert("scheduleSearch", pMap);
        log.info("스케줄Dao" + pMap);
        return result;
    }
}
