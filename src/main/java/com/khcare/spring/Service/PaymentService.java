package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    // 전체 결제 내역 출력
    List<Map<String,Object>> paymentList();

    // 결제 내역 추가
    int paymentInsert(Map<String,Object> pMap);

    // 결제 환불
    int paymentDelete(int payNo);
}
