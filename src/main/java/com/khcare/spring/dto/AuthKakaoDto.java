package com.khcare.spring.dto;

import lombok.Getter;

@Getter
public class AuthKakaoDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String refresh_token_expires_in;
}
