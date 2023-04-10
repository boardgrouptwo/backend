package com.khcare.spring.Service;

import com.khcare.spring.dao.MealDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealDao mealDao;

    @Override
    public List<Map<String, Object>> mealList(Map<String, Object> pMap){
        log.info("mealList 호출");

        List<Map<String, Object>> bList = null;
        bList = mealDao.mealList(pMap);

        return bList;
    }


}
