package com.khcare.spring.Service;

import com.khcare.spring.config.JwtTokenProvider;
import com.khcare.spring.dto.LoginDto;
import com.khcare.spring.dto.UserDto;
import com.khcare.spring.exception.LoginFailedException;
import com.khcare.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {
        logger.info(loginDto.getMem_id()+"");
        logger.info(loginDto.getPassword()+"");
        UserDto userDto = userMapper.findUserByUsername(loginDto.getMem_id())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword())) {
            throw new LoginFailedException("잘못된 비밀번호입니다");
        }
        logger.info("성공");
        return jwtTokenProvider.createToken(userDto.getPassword(), Collections.singletonList(userDto.getRole()), userDto.getUser_name());
    }
}
