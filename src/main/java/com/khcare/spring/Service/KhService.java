package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface KhService {

    //봉사활동 데이터 추가
    int serviceInsert(Map<String,Object> pMap);

    //매니저 봉사활동 리스트 조회
    List<Map<String,Object>> managerList(Map<String,Object> pMap);

}
