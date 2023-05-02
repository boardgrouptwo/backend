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

        return bList;
    }


    public int qnaInsert(Map<String, Object> pMap) {
        int result = 0;
        result = sqlSessionTemplate.update("qnaInsert", pMap);

        return result;
    }

    public int qnaUpdate(Map<String,Object> pMap){

        int result = 0;
        result = sqlSessionTemplate.update("qnaUpdate", pMap);

        return result;
    }

    public int repleInsert(Map<String, Object> pMap) {

        int result = 0;
        result = sqlSessionTemplate.update("repleInsert", pMap);

        return result;
    }

    public int qnaDelete(Map<String, Object> pMap){
        int result = 0;
        result = sqlSessionTemplate.update("qnaDelete", pMap);

        return result;
    }


    public List<Map<String, Object>> qnaAfterBefore(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = sqlSessionTemplate.selectList("qnaAfterBefore", pMap);

        return bList;
    }


}//end of class
