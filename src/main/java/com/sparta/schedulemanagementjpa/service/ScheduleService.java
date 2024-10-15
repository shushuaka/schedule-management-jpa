package com.sparta.schedulemanagementjpa.service;

import com.sparta.schedulemanagementjpa.entity.Schedule;
import com.sparta.schedulemanagementjpa.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 이 클래스가 비즈니스 로직을 처리하는 서비스 클래스임을 명시
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성 (저장)
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // 모든 일정 조회
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // 특정 일정 조회
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.")
        );
    }

    // 일정 수정
    public Schedule updateSchedule(Long id, Schedule scheduleDetails) {
        Schedule schedule = getScheduleById(id);
        schedule.setTitle(scheduleDetails.getTitle());
        schedule.setContent(scheduleDetails.getContent());
        schedule.setModifiedAt(scheduleDetails.getModifiedAt());

        return scheduleRepository.save(schedule);
    }

    // 일정 삭제
    public void deleteSchedule(Long id) {
        Schedule schedule = getScheduleById(id);
        scheduleRepository.delete(schedule);
    }
}
