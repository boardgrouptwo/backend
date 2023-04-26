package com.khcare.spring.Service;

import com.khcare.spring.dao.SponsorDao;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class SponsorServiceImpl implements SponsorService{
    Logger logger = LoggerFactory.getLogger(SponsorServiceImpl.class);
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

    @Override
    public int sponsorNo() {
        logger.info("sponsorNo 호출");
        int result = 0;

        result = sponsorDao.sponsorNo();

        return result;
    }

    @Override
    public int sponsorUserSum(Map<String,Object> pMap) {
        logger.info("sponsorUserSum 호출");
        int result = 0;

        result = sponsorDao.sponsorUserSum(pMap);

        return result;
    }

    public List<Map<String,Object>> sponStatistic(Map<String,Object> pMap) {
        logger.info("sponStatistic 호출");
        List<Map<String,Object>> rList = null;

        rList = sponsorDao.sponStatistic(pMap);

        return rList;
    }
}
