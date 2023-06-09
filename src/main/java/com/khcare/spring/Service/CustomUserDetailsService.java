package com.khcare.spring.Service;

import com.khcare.spring.dto.UserDto;
import com.khcare.spring.exception.UserNotFoundException;
import com.khcare.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //return userMapper.findByUserId(Long.valueOf(userId))
        log.info(userId);
        return userMapper.findByUserId(userId)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UserNotFoundException(userId + "> 찾을 수 없습니다."));
    }

    private UserDto addAuthorities(UserDto userDto) {
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));
        log.info(userDto.getRole());
        return userDto;
    }
}
