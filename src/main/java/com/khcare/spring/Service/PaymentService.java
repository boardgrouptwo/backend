package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    // 전체 결제 내역 출력
    List<Map<String,Object>> paymentList();

    // 결제번호를 통한 결제 내역 조회
    List<Map<String,Object>> paymentNumList(int payNo);

    // 특정 사용자의 결제 내역 조회
    List<Map<String,Object>> paymentUserList(String userId);

    // 특정 날짜 결제 내역 조회
    List<Map<String,Object>> paymentDateList(Map<String,Object> pMap);

    // 결제 내역 추가
    int paymentInsert(Map<String,Object> pMap);

    // 결제 환불
    int paymentDelete(int payNo);
}
