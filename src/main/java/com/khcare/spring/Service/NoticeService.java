package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    //공지사항 게시판 전체 조회
    List<Map<String,Object>> noticeList();

    //공지사항 게시판 검색
    List<Map<String,Object>> noticeSelectList();

    //공지사항 게시판 추가
    int noticeInsert();

    //공지사항 게시판 삭제
    int noticeDelete();

    //공지사항 게시판 수정
    int noticeUpdate();

}
