package com.khcare.spring.Service;

import com.khcare.spring.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestLogic {

    @Autowired
    private TestDao testDao;

    public List<Map<String, Object>> testList(Map<String, Object> pMap) {

        List<Map<String, Object>> bList = null;
        bList = testDao.testList(pMap);
        return bList;
    }
}
