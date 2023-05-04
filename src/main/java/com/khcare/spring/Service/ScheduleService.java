package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    //일정 조회
    List<Map<String, Object>> scheduleList(Map<String, Object> pMap);
    //일정 취소
    int scheduleDelete(Map<String, Object> pMap);

    //일정 수정
    int scheduleUpdate(Map<String, Object>pMap);

    //일정 추가
    int scheduleInsert(Map<String, Object>pMap);

    int scheduleSearch(Map<String, Object> pMap);
}
