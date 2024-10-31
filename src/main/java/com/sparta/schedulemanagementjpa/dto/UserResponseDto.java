package com.sparta.schedulemanagementjpa.dto;

import lombok.Getter;

@Getter

public class UserResponseDto {
    private final String userName;
    private final String email;
    private String token;

    public UserResponseDto(String userName, String email, String token) {
        this.userName = userName;
        this.email = email;
        this.token = token;
    }

    // 토큰 없이 초기화하는 생성자
    public UserResponseDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}