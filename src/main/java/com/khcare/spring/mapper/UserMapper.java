package com.khcare.spring.mapper;

import com.khcare.spring.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface UserMapper {

    // 로그인시 Username 검색
    Optional<UserDto> findUserByUsername(String username);
    // 로그인시 UserId 검색
    Optional<UserDto> findByUserId(String userId);

    // Id 찾기
    Map<String,Object> findId(Map<String, Object> pMap);

    // 비밀번호 찾기
    Map<String, Object> findPw(Map<String, Object> pMap);

    // 비밀번호 변경
    int changePw(Map<String,Object> pMap);

    // 회원가입
    void save(UserDto userDto);

    // 사용자 정보 찾기
    Map<String,Object> userInfo(Map<String,Object> pMap);

    // 회원 중복검사
    Map<String, Object> duplicateCheck(Map<String, Object> pMap);

    // 사용자 정보 수정
    int userUpdate(Map<String, Object> pMap);
}
