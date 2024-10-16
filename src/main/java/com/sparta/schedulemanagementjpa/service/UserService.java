package com.sparta.schedulemanagementjpa.service;

import com.sparta.schedulemanagementjpa.dto.UserRequestDto;
import com.sparta.schedulemanagementjpa.dto.UserResponseDto;
import com.sparta.schedulemanagementjpa.entity.User;
import com.sparta.schedulemanagementjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());

        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto(user.getUserName(), user.getEmail());
        return userResponseDto;
    }

    // 모든 유저 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 특정 유저 조회
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
    }

    // 유저 수정
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        user.setModifiedAt(userDetails.getModifiedAt());

        return userRepository.save(user);
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}