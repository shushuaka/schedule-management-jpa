package com.sparta.schedulemanagementjpa.service;

import com.sparta.schedulemanagementjpa.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementjpa.dto.ScheduleResponseDto;
import com.sparta.schedulemanagementjpa.entity.Schedule;
import com.sparta.schedulemanagementjpa.entity.User;
import com.sparta.schedulemanagementjpa.repository.ScheduleRepository;
import com.sparta.schedulemanagementjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // 이 클래스가 비즈니스 로직을 처리하는 서비스 클래스임을 명시
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 페이징 조회
    public Page<ScheduleResponseDto> getPagedSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        return scheduleRepository.findAll(pageable)
        .map(schedule -> new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUserName()
        ));
    }

    // 일정 생성 (저장)
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {

        // User ID를 사용하여 User 엔티티를 찾음
        User user = userRepository.findById(scheduleRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // DTO -> 엔티티 변환
        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setUserName(user.getUserName());
        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setContent(scheduleRequestDto.getContent());

        // 엔티티 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 엔티티 -> DTO 변환 후 반환
        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getUserName()
        );
    }

    // 모든 일정 조회
    public List<ScheduleResponseDto> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getUserName()
                ))
                .collect(Collectors.toList());
    }

    // 특정 일정 조회
    public ScheduleResponseDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUserName()
        );
    }

    // 일정 수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // DTO의 데이터를 엔티티에 반영
        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setContent(scheduleRequestDto.getContent());
        schedule.setModifiedAt(scheduleRequestDto.getModifiedAt());

        // 수정된 엔티티 저장
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        // 엔티티 -> DTO 변환 후 반환
        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getTitle(),
                updatedSchedule.getContent(),
                updatedSchedule.getUserName()
        );
    }

    // 일정 삭제
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        scheduleRepository.delete(schedule);
    }
}
