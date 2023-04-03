package com.khcare.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PaymentDao {
    Logger logger = LoggerFactory.getLogger(PaymentDao.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int paymentInsert(Map<String, Object> pMap) {
        logger.info("paymentInsert 호출");
        int result = 0;

        result = sqlSessionTemplate.insert("paymentInsert", pMap);
        logger.info(Integer.toString(result));

        return result;
    }

    public List<Map<String, Object>> paymentList() {
        logger.info("paymentList 호출");
        List<Map<String, Object>> rList = null;

        rList = sqlSessionTemplate.selectList("paymentList");
        logger.info(rList.toString());

        return rList;
    }

    public List<Map<String, Object>> paymentNumList(int payNo) {
        logger.info("paymentNumList 호출");
        List<Map<String, Object>> rList = null;

        rList = sqlSessionTemplate.selectOne("paymentNumList", payNo);
        logger.info(rList.toString());

        return rList;
    }

    public List<Map<String, Object>> paymentUserList(String userId) {
        logger.info("paymentUserList 호출");
        List<Map<String, Object>> rList = null;

        rList = sqlSessionTemplate.selectList("paymentUserList", userId);
        logger.info(rList.toString());

        return rList;
    }

    public List<Map<String, Object>> paymentDateList(Map<String, Object> pMap) {
        logger.info("paymentDateList 호출");
        List<Map<String, Object>> rList = null;

        rList = sqlSessionTemplate.selectList("paymentDateList", pMap);
        logger.info(rList.toString());

        return rList;
    }

    public int paymentDelete(int payNo) {
        logger.info("paymentDelete 호출");
        int result = 0;

        result = sqlSessionTemplate.delete("paymentDelete", payNo);
        logger.info(Integer.toString(result));

        return result;
    }
}
