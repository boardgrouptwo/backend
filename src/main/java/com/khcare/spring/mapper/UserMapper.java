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

    // Id 찾기 시도
    Map<String,Object> findId(Map<String, Object> pMap);

    // 회원가입
    void save(UserDto userDto);
}
