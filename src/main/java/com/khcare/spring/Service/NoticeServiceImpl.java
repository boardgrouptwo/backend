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
    public List<Map<String, Object>> noticeSelectList() {
        return null;
    }

    @Override
    public int noticeInsert() {
        return 0;
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
