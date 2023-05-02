package com.khcare.spring.Service;

import com.khcare.spring.dao.VisitManagerDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class VisitManagerServiceImpl implements VisitManagerService {
    @Autowired
    private VisitManagerDao visitManagerDao;
    @Override
    public int visitMInsert(Map<String, Object> pMap) {
        int result = 0;
        result = visitManagerDao.visitMInsert(pMap);
        return 0;
    }

    @Override
    public int visitMUpdatet(Map<String, Object> pMap) {
        int result = 0;
        result = visitManagerDao.visitMUpdate(pMap);
        return result;
    }



    @Override
    public List<Map<String, Object>> visitMList(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = visitManagerDao.visitMList(pMap);
        log.info("Impl"+pMap);
        return bList;
    }
    @Override
    public int visitMDelete(Map<String, Object> pMap) {

        int result =0;
        result = visitManagerDao.visitMDelete(pMap);
        return result;
    }
}
