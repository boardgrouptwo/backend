package com.khcare.spring.Service;

import com.khcare.spring.dao.ServiceDao;
import com.khcare.spring.dao.VisitDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@Log4j2
public class VisitServiceImpl implements VisitService{

@Autowired
private VisitDao visitDao;


    @Override
    public List<Map<String, Object>> visitList(Map<String, Object> pMap) {
        List<Map<String, Object>>bList = null;
        if(pMap.get("page")!=null){
            int num = Integer.parseInt(pMap.get("page").toString());
            pMap.put("page",(num-1)*5);
        }
        log.info(pMap);
        log.info("visitinsertImpl호출");
        bList = visitDao.visitList(pMap);
        return bList;
    }

    @Override
    public int visitDelete(Map<String, Object> pMap) {
        return 0;
    }

    @Override
    public int visitUpdate(Map<String, Object> pMap) {
        int result=0;
        result = visitDao.visitUpdate(pMap);
        return result;


    }

    @Override
    public int visitInsert(Map<String, Object> pMap) {
        int result = 0;
        result = visitDao.visitInsert(pMap);
        return result;

    }

}
