package com.khcare.spring.Service;

import com.khcare.spring.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<Map<String, Object>> noticeList(Map<String,Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = noticeDao.noticeList(pMap);
        return bList;
    }

    @Override
    public List<Map<String, Object>> noticeSearchList(Map<String,Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = noticeDao.noticeList(pMap);
        return bList;
    }

    @Override
    public int noticeHit(int notice_no) {
        int result = 0;
        result = noticeDao.noticeHit(notice_no);
        return result;
    }

    @Override
    public List<Map<String, Object>> noticeAfterBefore(Map<String, Object> pMap) {
        List<Map<String, Object>> bList = null;
        bList = noticeDao.noticeAfterBefore(pMap);
        return bList;
    }

    @Override
    public int noticeInsert(Map<String, Object> pMap) {
        int result = 0;
        result = noticeDao.noticeInsert(pMap);
        return result;
    }


    @Override
    public int noticeDelete() {
        return 0;
    }

    @Override
    public int noticeUpdate() {
        return 0;
    }
}
