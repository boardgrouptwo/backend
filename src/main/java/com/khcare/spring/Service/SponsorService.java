package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface SponsorService {

    //후원 데이터 추가
    int sponsorFormInsert(Map<String,Object> pMap);

    //공지사항 게시판 전체 조회
    List<Map<String,Object>> sponsorList(Map<String,Object> pMap);

    // 후원 테이블 spon_no값 확인
    int sponsorNo();

    // 사용자 총 후원 금액
    int sponsorUserSum(Map<String,Object> pMap);
}


