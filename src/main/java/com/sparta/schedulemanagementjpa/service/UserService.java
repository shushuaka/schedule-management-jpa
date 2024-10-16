package com.sparta.schedulemanagementjpa.service;

import com.sparta.schedulemanagementjpa.dto.UserRequestDto;
import com.sparta.schedulemanagementjpa.dto.UserResponseDto;
import com.sparta.schedulemanagementjpa.entity.User;
import com.sparta.schedulemanagementjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getUserName(), savedUser.getEmail());
    }

    // 모든 유저 조회
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = users.stream()
                .map(user -> new UserResponseDto(user.getUserName(), user.getEmail()))
                .collect(Collectors.toList());
        return userResponseDtos;
    }

    // 특정 유저 조회
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

        return new UserResponseDto(user.getUserName(), user.getEmail());
    }

    // 유저 수정
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());
        user.setModifiedAt(LocalDateTime.now());

        User updateUser = userRepository.save(user);

        return new UserResponseDto(updateUser.getUserName(), updateUser.getEmail());
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}