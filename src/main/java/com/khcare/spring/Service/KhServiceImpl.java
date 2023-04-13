package com.khcare.spring.Service;


import com.khcare.spring.dao.ServiceDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}