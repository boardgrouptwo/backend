package com.khcare.spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoPayDto {
    // 파라미터
    private String pay_type;                    // 결제 타입 (결제, 후원)
    private String partner_order_id;            // 가맹점 주문번호
    private String partner_user_id;             // 가맹점 회원 id
    private String item_name;                   // 상품명
    private int total_amount;                   // 상품 총액
    private String user_tel;                    // 사용자 연락처

    // 후원 시 사용하는 데이터
    private String spon_open;                   // 익명 여부
    private String spon_content;                // 후원 내용
    private String spon_pay;                    // 결제 종류 (홈페이지 결제)

    // 결제 요청 시 반환값
    private String tid;                         // 결제 고유 번호
    private String pg_token;                    // 결제승인 요청을 인증하는 토큰
    private String next_redirect_mobile_url;    // 카카오톡 결제 페이지 (모바일)
    private String next_redirect_pc_url;        // 카카오톡 결제 페이지 (PC)
    private String created_at;                  // 결제 준비 요청 시간

    // 결제 승인 완료 시 반환값
    private String aid;                         // 요청 고유 번호
    private String approved_at;                 // 결제 승인 시각
    private String payload;                     // 결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
}
