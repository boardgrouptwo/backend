package com.khcare.spring.Service;

import com.khcare.spring.dao.MealDao;
import com.khcare.spring.mapper.MealMapper;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.khcare.spring.dto.Meal;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;



@Log4j2
@Service
public class MealServiceImpl implements MealService {

        @Autowired
        private MealDao mealDao;

        @Override
        public List<Map<String, Object>> mealList(Map<String, Object> pMap){

            List<Map<String, Object>> bList = null;
            bList = mealDao.mealList(pMap);

           return bList;
        }

}//end of class
