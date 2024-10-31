package com.sparta.schedulemanagementjpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long scheduleId;
    private String userName;
    private String content;
}
