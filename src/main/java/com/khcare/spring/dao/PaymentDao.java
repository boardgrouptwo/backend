package com.khcare.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

    public List<Map<String, Object>> paymentList(Map<String, Object> pMap) {
        logger.info("paymentList 호출");
        List<Map<String, Object>> qList = new ArrayList<Map<String, Object>>();

        String listType = pMap.get("pay_type").toString();
        pMap.put("pay_type", listType);
        logger.info("검색 분류(pay_type) ===> " + pMap.get("pay_type").toString());

        if (listType.equals("전체")) {
            qList = sqlSessionTemplate.selectList("paymentList", pMap);
        }
        else if (listType.equals("결제")) {
            qList = sqlSessionTemplate.selectList("paymentListP", pMap);
        }
        else if (listType.equals("후원")) {
            qList = sqlSessionTemplate.selectList("paymentListS", pMap);
        }

        // 결제 금액 단위 나누기
        List<Map<String,Object>> rList = new ArrayList<Map<String,Object>>();
        for (int i=0; i<qList.size(); i++) {
            Map<String, Object> tMap = qList.get(i);
            int mInt = Integer.parseInt(tMap.get("pay_amount").toString());

            // 숫자에 천단위 콤마찍기 (금액 표기하기)
            DecimalFormat df = new DecimalFormat("###,###");
            String money = df.format(mInt) + " 원";

            tMap.put("pay_amount", money);
            rList.add(tMap);
        }

        logger.info("쿼리결과 : " + rList.toString());

        return rList;
    }

    public List<Map<String, Object>> paymentListPre(Map<String, Object> pMap) {
        logger.info("paymentListPre 호출");
        List<Map<String, Object>> qList = new ArrayList<Map<String, Object>>();

        qList = sqlSessionTemplate.selectList("paymentListPreview", pMap);

        // 결제 금액 단위 나누기
        List<Map<String,Object>> rList = new ArrayList<Map<String,Object>>();
        for (int i=0; i<qList.size(); i++) {
            Map<String, Object> tMap = qList.get(i);
            int mInt = Integer.parseInt(tMap.get("pay_amount").toString());

            // 숫자에 천단위 콤마찍기 (금액 표기하기)
            DecimalFormat df = new DecimalFormat("###,###");
            String money = df.format(mInt) + " 원";

            tMap.put("pay_amount", money);
            rList.add(tMap);
        }

        logger.info("쿼리결과 : " + rList.toString());

        return rList;
    }

    public int paymentDelete(int payNo) {
        logger.info("paymentDelete 호출");
        int result = 0;

        result = sqlSessionTemplate.delete("paymentDelete", payNo);
        logger.info(Integer.toString(result));

        return result;
    }

    public int paymentNo() {
        logger.info("paymentNo 호출");
        int result = 0;
        Map<String,Object> rMap = null;

        rMap = sqlSessionTemplate.selectOne("paymentNo");

        result = Integer.parseInt(rMap.get("pay_no").toString());
        logger.info(Integer.toString(result));

        return result;
    }
}
