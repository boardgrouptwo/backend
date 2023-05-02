package com.khcare.spring.Service;

import com.khcare.spring.dao.ElderDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ElderServiceImpl implements ElderService {
    Logger log = LogManager.getLogger(ElderServiceImpl.class);
    @Autowired
    ElderDao elderDao;


    @Override
    public int elderJoin(Map<String, Object> pMap) {
        log.info("elderJoin 호출");
        int result = 0;
        result = elderDao.elderJoin(pMap);
        return result;
    }

    @Override
    public int elderInsert(Map<String, Object> pMap) {
        log.info("elderInsert 호출");
        int result = 0;

        result = elderDao.elderInsert(pMap);
        log.info(String.valueOf(result));
        return result;
    }

    @Override
    public Map<String, Object> elderSelect(Map<String, Object> pMap) {
        log.info("elderSelect 호출");
        Map<String, Object> rMap = null;

        rMap = elderDao.elderSelect(pMap);

        return rMap;
    }
}
