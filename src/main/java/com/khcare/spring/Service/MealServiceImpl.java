package com.khcare.spring.Service;

import com.google.gson.Gson;
import com.khcare.spring.dto.MealDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class MealServiceImpl implements MealService {

    private static final String URL = "https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=1ef10725de844af58e929578cae1adf2&Type=json&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7530620";

  /*  public List<MealDto.MealItem> getMealInfo(String schoolCode, String date){
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format(URL, schoolCode, date);
        String jsonString = restTemplate.getForObject(url, String.class);
        log.info(url);
        log.info(jsonString);
        Gson gson = new Gson();

        MealDto mealDto = gson.toJson(jsonString, MealDto.class);

        List<MealDto.MealItem> mealItems = null;
        log.info(mealDto);


        if(mealDto != null && mealDto.getItems() != null && mealDto.getItems().getItem() != null){
            mealItems = mealDto.getItems().getItem();
            log.info(mealItems);
        }

        return mealItems;
    }*/

    public  String  getMealInfo(String schoolCode, String date){
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format(URL, schoolCode, date);
        String jsonString = restTemplate.getForObject(url, String.class);
            log.info(url);
            log.info(jsonString);

            return jsonString;
    }


/*    @Override
    public List<Map<String, Object>> mealList(Map<String, Object> pMap){
        log.info("mealList 호출");

        String schoolCode = (String)pMap.get("schoolCode");
        String date = (String)pMap.get("date");

        List<MealDto.MealItem> mealItems = getMealInfo((String)pMap.get("schoolCode"), (String)pMap.get("date"));
        log.info(mealItems);

        List<Map<String, Object>> bList = new ArrayList<>();

        if(mealItems != null) {
            for (MealDto.MealItem item : mealItems) {
                Map<String, Object> rmap = new HashMap<>();
                rmap.put("mealDate", item.getMealDate());
                rmap.put("mealName", item.getMealName());
                rmap.put("dishName", item.getDishName());
                rmap.put("originInfo", item.getOriginInfo());
                rmap.put("calorieInfo", item.getCalorieInfo());
                rmap.put("nutrientInfo", item.getNutrientInfo());
                bList.add(rmap);

            }
            log.info(bList);
        }
        return bList;
    }*/

    @Override
    public String mealList(Map<String, Object> pMap){
        log.info("mealList 호출");

        String schoolCode = (String)pMap.get("schoolCode");
        String date = (String)pMap.get("date");

        List<MealDto.MealItem> mealItems = getMealInfo((String)pMap.get("schoolCode"), (String)pMap.get("date"));
            log.info(mealItems);

        List<Map<String, Object>> bList = new ArrayList<>();

        if(mealItems != null) {
            for (MealDto.MealItem item : mealItems) {
                Map<String, Object> rmap = new HashMap<>();
                    rmap.put("mealDate", item.getMealDate());
                    rmap.put("mealName", item.getMealName());
                    rmap.put("dishName", item.getDishName());
                    rmap.put("originInfo", item.getOriginInfo());
                    rmap.put("calorieInfo", item.getCalorieInfo());
                    rmap.put("nutrientInfo", item.getNutrientInfo());
                    bList.add(rmap);

            }
            log.info(bList);
        }
        return bList;
    }

}//end of class
