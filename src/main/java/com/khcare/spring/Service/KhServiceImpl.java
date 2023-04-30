package com.khcare.spring.Service;


import com.khcare.spring.dao.ServiceDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class KhServiceImpl implements KhService{

    @Autowired
    private ServiceDao serviceDao;

    @Override
    public int serviceInsert(Map<String, Object> pMap) {
        int result =0;
        result = serviceDao.serviceInsert(pMap);
        return result;
    }

    @Override
    public int serviceUpdate(Map<String, Object> pMap) {
        int result = 0;
        result = serviceDao.serviceUpdate(pMap);
        return result;
    }

    @Override
    public List<Map<String, Object>> managerList(Map<String,Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = serviceDao.managerList(pMap);
        return bList;
    }

    @Override
    public int serviceDelete(Map<String, Object> pMap) {
        int result =0;
        result = serviceDao.serviceDelete(pMap);
        return result;
    }

    @Override
    public Map<String, Object> userDate(Map<String, Object> pMap) {
        log.info("userDate 호출");
        Map<String, Object> rMap = null;

        rMap = serviceDao.userDate(pMap);

        return rMap;
    }

    @Override
    public int reviewInsert(Map<String, Object> pMap) {
        int result =0;
        result = serviceDao.reviewInsert(pMap);
        return result;
    }

    @Override
    public List<Map<String, Object>> reviewList(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        if(pMap.get("page") != null) {
            int num = Integer.parseInt(pMap.get("page").toString());
            pMap.put("page", (num-1)*10);
        }
        log.info(pMap);
        bList = serviceDao.reviewList(pMap);
        return bList;
    }

}
