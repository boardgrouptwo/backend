package com.khcare.spring.Service;

import java.util.List;
import java.util.Map;

public interface QnAService {

    //QnA 게시판 전체 조회
    List<Map<String, Object>> qnaList(Map<String, Object> pMap);

    //QnA 게시판 검색
    List<Map<String, Object>> qnaSearchList(Map<String, Object> pMap);

    //QnA 게시판 이전 이후 게시글 가져오기
    List<Map<String, Object>> qnaAfterBefore(Map<String, Object> pMap);

    //QnA 게시판 추가
    int qnaInsert(Map<String, Object> pMap);

    //QnA 게시판 삭제
    int qnaDelete(Map<String, Object> pMap);

    //QnA 게시판 수정
    int qnaUpdate(Map<String, Object> pMap);

}//end of Interface
