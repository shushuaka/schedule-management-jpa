package com.sparta.schedulemanagementjpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

public class UserRequestDto {

    @NotBlank(message = "유저명은 필수 입력 값입니다.")
    @Size(max = 20, message = "유저명은 최대 20자까지 입력할 수 있습니다.")
    private String userName;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;


}
