package com.khcare.spring.Service;

import com.khcare.spring.dto.UserVO;

import java.util.Map;

public interface UserLogic {

    //로그인
    UserVO getUserAccount(String userId);

    //회원가입
}
