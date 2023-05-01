package com.khcare.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayImpDto {
    // 파라미터
    private String payType;                    // 결제 타입 (결제, 후원)
    private String partnerOrderId;            // 가맹점 주문번호
    private String partnerUserId;             // 가맹점 회원 id
    private String itemName;                   // 상품명
    private int totalAmount;                   // 상품 총액
    private String userTel;                    // 사용자 연락처

    // 후원 시 사용하는 데이터
    private String sponOpen;                   // 익명 여부
    private String sponContent;                // 후원 내용
    private String sponPay;                    // 결제 종류 (홈페이지 결제)

    // 결제 시 사용하는 데이터
    private String reqText;                    // 배송 요청사항
    private String userAddr;                   // 배송 주소

    // 결제 요청 시 반환값
    private String tid;                         // 결제 고유 번호
    private String pgToken;                    // 결제승인 요청을 인증하는 토큰
    private String redirect_mobile_url;    // 카카오톡 결제 페이지 (모바일)
    private String redirect_pc_url;        // 카카오톡 결제 페이지 (PC)
    private String createdAt;                  // 결제 준비 요청 시간

    // 결제 승인 완료 시 반환값
    private String aid;                         // 요청 고유 번호
    private String approvedAt;                 // 결제 승인 시각
    private String payload;                     // 결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
}
