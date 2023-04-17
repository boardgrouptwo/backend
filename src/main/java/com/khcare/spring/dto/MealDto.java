package com.khcare.spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class MealDto {
    private MealItems items;


    @Data
    public static class MealItems {
        private List<MealItem> item;

    }

    @Data
    public static class MealItem {
        private String mealDate;
        private String mealName; //식사명
        private String dishName; //요리명
        private String originInfo; //원산지명
        private String calorieInfo; //칼로리 정보
        private String nutrientInfo; //영양 정보
    }

}//end of class

