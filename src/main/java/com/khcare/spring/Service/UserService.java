package com.khcare.spring.Service;

import com.khcare.spring.config.JwtTokenProvider;
import com.khcare.spring.dao.ElderDao;
import com.khcare.spring.dao.UserDao;
import com.khcare.spring.dto.LoginDto;
import com.khcare.spring.dto.UserDto;
import com.khcare.spring.exception.DuplicatedUserIdException;
import com.khcare.spring.exception.LoginFailedException;
import com.khcare.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        logger.info(userDto+"");
        logger.info(userDto.getUser_id());
        logger.info(userDto.getUser_name());
        logger.info(userDto.getPassword());
        logger.info(userDto.getRole());

        //return jwtTokenProvider.createToken(userDto.getPassword(), Collections.singletonList(userDto.getRole()), userDto.getUser_name());
        return jwtTokenProvider.createToken(userDto.getUser_id(), Collections.singletonList(userDto.getRole()), userDto.getUser_name());
    }

    @Transactional
    public UserDto join(UserDto userDto) {
        if (userMapper.findUserByUsername(userDto.getUser_id()).isPresent()) {
            throw new DuplicatedUserIdException("이미 가입된 유저입니다");
        }
        logger.info(userDto.toString());

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapper.save(userDto);

        return userMapper.findUserByUsername(userDto.getUser_id()).get();
    }

    /*
    * 아이디 찾기    *
    */
    public Map<String, Object> findId(Map<String,Object> pMap) {
        Map<String, Object> userId = userMapper.findId(pMap);
        logger.info(userId+"");
        return userId;
    }

    /*
     * 비밀번호 찾기
     */
    public int findPw(Map<String,Object> pMap) {
        Map<String, Object> userid = userMapper.findPw(pMap);
        int result = 0;
        try {
            if(userid.get("user_id").toString().equals(pMap.get("user_id").toString())) {
                result = 1;
            }
        } catch (NullPointerException e) {

        }

        return result;
    }

    /*
    * 비밀번호 변경
    */
    public int changePw(Map<String,Object> pMap) {
        int result =0;
        String password = pMap.get("user_password").toString();
        // 패스워드 인코딩
        pMap.put("user_password",passwordEncoder.encode(password));
        logger.info(pMap+"");
        result = userMapper.changePw(pMap);
        return result;
    }

    public Map<String, Object> userInfo(Map<String, Object> pMap) {
        logger.info("userInfo 호출");
        Map<String, Object> rMap = null;

        rMap = userMapper.userInfo(pMap);

        return rMap;
    }

    public int duplicateCheck(Map<String, Object> pMap) {
        int result = 0;
        Map<String, Object> rMap = null;
        rMap = userMapper.duplicateCheck(pMap);
        logger.info(rMap+"");
        try {
            if(rMap.size()!=0) {
                result = 1;
            }
        } catch (NullPointerException e) {
            result = 0;
        }
        return result;
    }

    public int userUpdate(Map<String, Object> pMap) {
        logger.info("userUpdate 호출");
        int result = 0;

        result = userMapper.userUpdate(pMap);

        return result;
    }
}
