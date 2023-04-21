package com.khcare.spring.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class SponsorDao {
    Logger logger = LoggerFactory.getLogger(SponsorDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int sponsorFormInsert(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("sponsorFormInsert", pMap);
        return result;
    }

    public List<Map<String, Object>> sponsorList(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("sponsorList", pMap);
        return bList;
    }

    public int sponsorNo() {
        logger.info("paymentNo 호출");
        int result = 0;

        result = sqlSessionTemplate.selectOne("sponsorNo");
        logger.info(Integer.toString(result));

        return result;
    }
}
