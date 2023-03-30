package com.khcare.spring.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class NoticeDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> noticeList(Map<String,Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("noticeList", pMap);
       // log.info(bList);
        return bList;
    }

    public int noticeHit(int notice_no) {
        int result = 0;
        result = sqlSessionTemplate.update("noticeHit",notice_no);
        log.info("조회수 증가");
        log.info(result);
        return result;
    }


    public List<Map<String, Object>> noticeAfterBefore(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("noticeAfterBefore", pMap);
        // log.info(bList);
        return bList;
    }
}
