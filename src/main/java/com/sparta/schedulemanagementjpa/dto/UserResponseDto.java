package com.sparta.schedulemanagementjpa.dto;

import lombok.Getter;

@Getter

public class UserResponseDto {
    private final String userName;
    private final String email;

    public UserResponseDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
