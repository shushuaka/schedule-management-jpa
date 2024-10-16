package com.sparta.schedulemanagementjpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class ScheduleRequestDto {

    private Long userId;
    private String title;
    private String content;
    private LocalDateTime ModifiedAt;
}
