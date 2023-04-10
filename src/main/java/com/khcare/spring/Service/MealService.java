package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface MealService {

    List<Map<String, Object>> mealList(Map<String, Object> pMap);
}
