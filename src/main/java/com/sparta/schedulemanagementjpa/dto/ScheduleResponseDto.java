package com.sparta.schedulemanagementjpa.dto;

import lombok.Getter;

@Getter

public class ScheduleResponseDto {
    private final long id;
    private final String title;
    private final String content;
    private final String userName;

    public ScheduleResponseDto(Long id, String title, String content, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}
