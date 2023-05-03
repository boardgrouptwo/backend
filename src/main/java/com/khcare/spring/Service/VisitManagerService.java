package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface VisitManagerService {
    int visitMInsert(Map<String, Object> pMap);

    int visitMUpdatet(Map<String, Object> pMap);



    List<Map<String, Object>> visitMList(Map<String, Object> pMap);

    int visitMDelete(Map<String, Object> pMap);
}
