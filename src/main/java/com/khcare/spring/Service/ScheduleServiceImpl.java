package com.khcare.spring.Service;

import com.khcare.spring.dao.ScheduleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
   private ScheduleDao scheduleDao;

    @Override
    public List<Map<String, Object>> scheduleList(Map<String, Object> pMap) {
        List<Map<String, Object>> sList = null;
        if (pMap.get("page") != null) {
            int num = Integer.parseInt(pMap.get("page").toString());
            pMap.put("page", (num - 1) * 10);
        }
        log.info(pMap);
        sList = scheduleDao.scheduleList(pMap);
        log.info("scheduleList호출"+sList);
        return sList;
    }

    @Override
    public int scheduleDelete(Map<String, Object> pMap) {
        int result = 0;
        result = scheduleDao.scheduleDelete(pMap);
        return result;
    }

    @Override
    public int scheduleUpdate(Map<String, Object> pMap) {

        int result = 0;
        result = scheduleDao.scheduleUpdate(pMap);
        return result;
    }

    @Override
    public int scheduleInsert(Map<String, Object> pMap) {
        int result = 0;
        log.info("scheduleInsert"+pMap);
        result = scheduleDao.scheduleInsert(pMap);
        return result;
    }

    @Override
    public int scheduleSearch(Map<String, Object> pMap) {
        int result = 0;
        result = scheduleDao.scheduleSearch(pMap);
        return result;
    }
}