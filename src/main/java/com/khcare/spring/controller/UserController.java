package com.khcare.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.khcare.spring.Service.GoogleService;
import com.khcare.spring.Service.KakaoUserService;
import com.khcare.spring.Service.ResponseService;
import com.khcare.spring.Service.UserService;
import com.khcare.spring.dto.BaseResponse;
import com.khcare.spring.dto.LoginDto;
import com.khcare.spring.dto.SingleDataResponse;
import com.khcare.spring.dto.UserDto;
import com.khcare.spring.exception.DuplicatedUserIdException;
import com.khcare.spring.exception.LoginFailedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;
    private final KakaoUserService kakaoUserService;
    private final GoogleService googleService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        logger.info(loginDto+"");

        ResponseEntity responseEntity = null;
        String access_token = null;
        try {
            access_token = userService.login(loginDto);
            logger.info("login 성공");

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

   @PostMapping("/googleLogin")
    public String GoogleLogin(@RequestBody Map<String,Object> pMap) {
        logger.info(pMap+"");
        String access_token=pMap.get("access_token").toString();
        logger.info(access_token+"");
       String token = null;
        try {
            token = googleService.getUserInfo(access_token);

        } catch(IOException e) {

        }
        return token;
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserDto userDto) {
        ResponseEntity responseEntity = null;
        logger.info(userDto+"");
        try {
            UserDto savedUser = userService.join(userDto);
            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "회원가입 성공", savedUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicatedUserIdException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @PostMapping("/findId")
    public Map<String, Object> findId(@RequestBody Map<String,Object> pMap) {

        Map<String, Object> userId = userService.findId(pMap);
        return userId;
    }

    @PostMapping("/findPw")
    public int findPw(@RequestBody Map<String,Object> pMap) {
        logger.info(pMap+"");
        int result = 0;
        result = userService.findPw(pMap);
        return result;
    }

    @PostMapping("/changePw")
    public int changePw(@RequestBody Map<String, Object> pMap) {
        int result = 0;
        result = userService.changePw(pMap);
        logger.info(result+"");
        return result;
    }
}
