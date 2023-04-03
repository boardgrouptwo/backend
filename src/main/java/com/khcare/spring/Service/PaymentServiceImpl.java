package com.khcare.spring.Service;

import com.khcare.spring.dao.PaymentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{
    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int paymentInsert(Map<String, Object> pMap) {
        logger.info("paymentInsert 호출");
        int result = 0;

        result = paymentDao.paymentInsert(pMap);

        return result;
    }

    @Override
    public List<Map<String, Object>> paymentList() {
        logger.info("paymentList 호출");
        List<Map<String, Object>> rList = null;

        rList = paymentDao.paymentList();

        return rList;
    }

    @Override
    public List<Map<String, Object>> paymentNumList(int payNo) {
        logger.info("paymentNumList 호출");
        List<Map<String, Object>> rList = null;

        rList = paymentDao.paymentNumList(payNo);

        return rList;
    }

    @Override
    public List<Map<String, Object>> paymentUserList(String userId) {
        logger.info("paymentUserList 호출");
        List<Map<String, Object>> rList = null;

        rList = paymentDao.paymentUserList(userId);

        return rList;
    }

    @Override
    public List<Map<String, Object>> paymentDateList(Map<String, Object> pMap) {
        logger.info("paymentDateList 호출");
        List<Map<String, Object>> rList = null;

        rList = paymentDao.paymentDateList(pMap);

        return rList;
    }

    @Override
    public int paymentDelete(int payNo) {
        logger.info("paymentDelete 호출");
        int result = 0;

        result = paymentDao.paymentDelete(payNo);

        return result;
    }
}
