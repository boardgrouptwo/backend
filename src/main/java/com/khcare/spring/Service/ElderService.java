package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface ElderService {
    // 내원자 추가
    int elderInsert(Map<String,Object> pMap);

    // 내원자 정보 호출
    Map<String,Object> elderSelect(Map<String,Object> pMap);
}
