package com.sparta.schedulemanagementjpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class ScheduleRequestDto {

    private Long userId;

    @NotBlank(message = "할일 제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "할일 내용은 필수 입력 값입니다.")
    private String content;
    private LocalDateTime ModifiedAt;
}
