package com.khcare.spring.dto;

import lombok.*;

import java.util.List;


@Data

public class ScheduleDto {


   private ScheduleItems items;

    @Data
    public static class ScheduleItems {
        private List<ScheduleItem> item;
    }

    @Data
    public static class ScheduleItem {
        private  String title;
        private  String writer;
        private  String content;
        private  String date;

    }
}
