package com.sparta.schedulemanagementjpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class CommentResponseDto {
    private Long id;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Long id, String userName, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
