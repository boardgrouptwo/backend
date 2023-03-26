package com.khcare.spring.dao;

import com.khcare.spring.dto.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public UserVO getUserAccount(String userId) {
        UserVO userVO = null;
        userVO = (UserVO)sqlSessionTemplate.selectList("getUserAccount", userId);
        return userVO;
    }
}
