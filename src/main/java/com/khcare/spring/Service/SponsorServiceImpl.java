package com.khcare.spring.Service;

import com.khcare.spring.dao.SponsorDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class SponsorServiceImpl implements SponsorService{

    @Autowired
    private SponsorDao sponsorDao;

    @Override
    public int sponsorFormInsert(Map<String, Object> pMap) {
        int result =0;
        result = sponsorDao.sponsorFormInsert(pMap);
        return result;
    }

    @Override
    public List<Map<String, Object>> sponsorList(Map<String,Object> pMap) {
        List<Map<String, Object>> bList = null;
        log.info(pMap);
        bList = sponsorDao.sponsorList(pMap);
        return bList;
    }
}
