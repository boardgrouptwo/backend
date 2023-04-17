package com.khcare.spring.Service;

import com.khcare.spring.dao.QnADao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class QnAServiceImpl implements QnAService{

    @Autowired
    private QnADao qnaDao;


    @Override
    public List<Map<String, Object>> qnaList(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;

        if(pMap.get("page") != null){
            int num = Integer.parseInt(pMap.get("page").toString());
            pMap.put("page", (num-1)*10);
        }
        log.info(pMap);

        bList = qnaDao.qnaList(pMap);

        return bList;
    }


    @Override
    public List<Map<String, Object>> qnaSearchList(Map<String, Object> pMap) {
        return null;
    }


    @Override
    public List<Map<String, Object>> qnaAfterBefore(Map<String, Object> pMap) {
        return null;
    }


    @Override
    public int qnaInsert(Map<String, Object> pMap) {
        return 0;
    }


    @Override
    public int qnaDelete(Map<String, Object> pMap) {
        return 0;
    }


    @Override
    public int qnaUpdate(Map<String, Object> pMap) {
        return 0;
    }

}//end of class
