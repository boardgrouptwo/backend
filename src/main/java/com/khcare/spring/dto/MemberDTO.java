package com.khcare.spring.dto;

import lombok.Data;

@Data
public class MemberDTO {
    //로그인 체크용
    private String userid;
    private String passwd;
    private String name;
    private String email;
}
