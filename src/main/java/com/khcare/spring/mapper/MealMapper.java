package com.khcare.spring.mapper;

import com.khcare.spring.dto.Meal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MealMapper {
    void mealInsert(@Param("meal") Meal meal);
}

