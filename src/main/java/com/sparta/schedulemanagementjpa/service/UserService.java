package com.sparta.schedulemanagementjpa.service;

import com.sparta.schedulemanagementjpa.config.JwtTokenProvider;
import com.sparta.schedulemanagementjpa.config.PasswordEncoder;
import com.sparta.schedulemanagementjpa.dto.UserRequestDto;
import com.sparta.schedulemanagementjpa.dto.UserResponseDto;
import com.sparta.schedulemanagementjpa.entity.User;
import com.sparta.schedulemanagementjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 유저 생성 (회원가입 추가 & JWT 발급)
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());

        // 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(savedUser.getEmail());

        // UserResponseDto에 JWT 토큰을 포함하여 반환
        return new UserResponseDto(savedUser.getUserName(), savedUser.getEmail(), token);
    }

    // 모든 유저 조회
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = users.stream()
                .map(user -> new UserResponseDto(user.getUserName(), user.getEmail()))
                .collect(Collectors.toList());
        return userResponseDtos;
    }

    // 특정 유저 조회
    @Transactional(readOnly = true)
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

        // 비밀번호도 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(encodedPassword);

        user.setModifiedAt(LocalDateTime.now());

        //save() 제거, 더티체킹으로 자동 업데이트
        return new UserResponseDto(user.getUserName(), user.getEmail());
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        userRepository.delete(user);
    }
}