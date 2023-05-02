package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface VisitService {


    //면회 조회
    List<Map<String, Object>> visitList(Map<String, Object> pMap);
    //면회 취소
    int visitDelete(Map<String, Object> pMap);

    //면회 수정
    int visitUpdate(Map<String, Object>pMap);

    //면회 추가
    int visitInsert(Map<String, Object>pMap);
}
