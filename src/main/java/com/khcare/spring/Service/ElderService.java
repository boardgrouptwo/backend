package com.khcare.spring.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ElderService {

    //어르신 가입
    int elderJoin(Map<String,Object> pMap);

    // 어르신 추가
    int elderInsert(Map<String,Object> pMap);

    // 내원자 정보 호출
    Map<String,Object> elderSelect(Map<String,Object> pMap);
}
