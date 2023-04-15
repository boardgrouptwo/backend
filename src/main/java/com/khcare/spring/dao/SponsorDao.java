package com.khcare.spring.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Log4j2
public class SponsorDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int sponsorFormInsert(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("sponsorFormInsert", pMap);
        return result;
    }
}
