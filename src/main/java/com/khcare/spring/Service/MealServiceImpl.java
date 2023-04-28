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
            log.info("mealList 호출");

            List<Map<String, Object>> bList = null;
            bList = mealDao.mealList(pMap);

           return bList;
        }

    public List<Meal> readCsv() throws IOException, IOException, CsvValidationException {
        String path = "D:/finalproject/meal.csv";
        Reader reader = Files.newBufferedReader(Paths.get(path));

        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<Meal> meals = new ArrayList<>();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            Meal meal = new Meal();
            meal.setMealNo(Integer.parseInt(line[0]));
            meal.setMealType(line[1]);
            meal.setMealOrigin(line[2]);
            meal.setMealCal(Integer.parseInt(line[3]));
            meal.setMealNut(line[4]);
            meal.setMealDate(line[5]);
            meals.add(meal);
        }
        csvReader.close();

        return meals;
    }//end of redadCsv

    public void mealInsert(List<Meal> meals) {
        mealDao.mealInsert(meals);
    }


}//end of class
