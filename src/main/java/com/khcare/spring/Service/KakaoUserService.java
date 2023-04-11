package com.khcare.spring.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khcare.spring.config.JwtTokenProvider;
import com.khcare.spring.config.Oauth2Kakao;
import com.khcare.spring.dto.AuthKakaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class KakaoUserService {

    private final Oauth2Kakao oauth2Kakao;
    private final JwtTokenProvider jwtTokenProvider;


    // 카카오로 인증받기
    public String oauth2AuthorizationKakao(String code) throws JsonProcessingException {
        AuthKakaoDto authorization = oauth2Kakao.callTokenApi(code);
        String userInfoFromKakao = oauth2Kakao.callGetUserByAccessToken(authorization.getAccess_token());
        // JSON 파서 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON 데이터를 JsonNode로 파싱
        JsonNode rootNode = objectMapper.readTree(userInfoFromKakao);

        // properties의 id, nickname 추출
        long id = rootNode.get("id").asLong();
        String nickname = rootNode.get("properties").get("nickname").asText();

        return jwtTokenProvider.createToken(String.valueOf(id), Collections.singletonList("USER"), nickname);

    }

}
