package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    //공지사항 게시판 전체 조회
    List<Map<String,Object>> noticeList(Map<String,Object> pMap);

    //공지사항 게시판 검색
    List<Map<String,Object>> noticeSearchList(Map<String,Object> pMap);

    //공지사항 게시판 조회수
    int noticeHit(int notice_no);

    //공지사항 이전 이후 게시글 가져오기
    List<Map<String, Object>> noticeAfterBefore(Map<String,Object> pMap);

    //공지사항 게시판 추가
    int noticeInsert(Map<String,Object> pMap);

    //공지사항 게시판 삭제
    int noticeDelete(Map<String,Object> pMap);

    //공지사항 게시판 수정
    int noticeUpdate(Map<String,Object> pMap);

}
