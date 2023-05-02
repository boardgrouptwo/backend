package com.khcare.spring.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//면회 예약 정보 담기 getter/setter
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {
    private String user_id;
    private String user_name;
    private String password;
    private String volume;
    private String elder_no;
    private String elder_id;
    private String time;
    private String date;


}
