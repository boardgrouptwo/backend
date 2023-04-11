package com.khcare.spring.mapper;

import com.khcare.spring.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDto> findUserByUsername(String username);
    Optional<UserDto> findByUserId(Long userId);
}
