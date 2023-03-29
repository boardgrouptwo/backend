package com.khcare.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

    private int pay_no;                 // 결제 번호
    private String user_id;             // 회원ID
    private int pay_amount;             // 결제 금액
    private String pay_date;            // 결제 날짜
    private String pay_content;         // 결제 내용
}
