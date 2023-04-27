package com.khcare.spring.dao;

import lombok.extern.log4j.Log4j2;
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

    public int sponsorUserSum(Map<String,Object> pMap) {
        logger.info("sponsorUserSum 호출");
        int result = 0;

        result = sqlSessionTemplate.selectOne("sponsorUserSum", pMap);
        logger.info(Integer.toString(result));

        return result;
    }

    public List<Map<String,Object>> sponStatistic(Map<String,Object> pMap) {
        logger.info("sponStatistic 호출");
        List<Map<String, Object>> qList = new ArrayList<Map<String, Object>>();

        if(pMap.get("page") != null){
            int num = Integer.parseInt(pMap.get("page").toString());
            pMap.put("page", (num-1)*10);
        }

        qList = sqlSessionTemplate.selectList("sponStatistic", pMap);

        // 결제 금액 단위 나누기
        List<Map<String,Object>> rList = new ArrayList<Map<String,Object>>();
        for (int i=0; i<qList.size(); i++) {
            Map<String, Object> tMap = qList.get(i);
            int mInt = Integer.parseInt(tMap.get("spon_money").toString());

            // 숫자에 천단위 콤마찍기 (금액 표기하기)
            DecimalFormat df = new DecimalFormat("###,###");
            String money = df.format(mInt) + " 원";

            tMap.put("spon_money", money);
            rList.add(tMap);
        }

        logger.info("쿼리결과 : " + rList.toString());

        return rList;
    }
}
