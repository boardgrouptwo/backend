package com.khcare.spring.Service;

import com.khcare.spring.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserLogicImpl userLogicImpl;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        UserVO userVo = userLogicImpl.getUserAccount(userId);
        if (userVo == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return userVo;
    }
}
