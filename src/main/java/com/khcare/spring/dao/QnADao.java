package com.khcare.spring.dao;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class QnADao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    public List<Map<String, Object>> qnaList(Map<String, Object> pMap){
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("qnaList", pMap);
        log.info(bList);

        return bList;
    }


}//end of class
