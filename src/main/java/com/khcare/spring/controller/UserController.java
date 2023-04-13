package com.khcare.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.khcare.spring.Service.KakaoUserService;
import com.khcare.spring.Service.ResponseService;
import com.khcare.spring.Service.UserService;
import com.khcare.spring.dto.LoginDto;
import com.khcare.spring.dto.SingleDataResponse;
import com.khcare.spring.exception.LoginFailedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;
    private final KakaoUserService kakaoUserService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        logger.info(loginDto+"");

        ResponseEntity responseEntity = null;
        String access_token = null;
        try {
            access_token = userService.login(loginDto);
            logger.info("login 성공");

/*            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            SingleDataResponse<String> response = responseService.getSingleDataResponse(true, "로그인 성공", token);
            responseEntity = ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);

            logger.info(token);
            logger.info("response : "+response);
            logger.info(responseEntity+"");*/

        } catch (LoginFailedException e) {
            String message = e.toString();
            int colonIndex = message.indexOf(":");
            if (colonIndex != -1) {
                access_token = message.substring(colonIndex + 1).trim();
            }

            logger.info(e+"");

        }
        return access_token;
    }

    /*
    * code : 카카오 서버로부터 받은 인가 코드
    *
    */
    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {

        String access_token = null;
        try {
            access_token = kakaoUserService.oauth2AuthorizationKakao(code);
            logger.info(access_token);

        } catch (Exception e) {
         e.printStackTrace();
        }

        return access_token;
    }

    @GetMapping("/auth")
    public @ResponseBody Authentication auth() {
        logger.info(SecurityContextHolder.getContext().getAuthentication()+"");
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
