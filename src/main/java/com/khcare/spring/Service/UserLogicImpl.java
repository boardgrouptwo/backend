package com.khcare.spring.Service;

import com.khcare.spring.dao.UserDao;
import com.khcare.spring.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserLogicImpl implements UserLogic {

    @Autowired
    private UserDao userDao;

    @Override
    public UserVO getUserAccount(String userId) {
        UserVO userVO = userDao.getUserAccount(userId);
        return userVO;
    }
}
