package com.sparta.schedulemanagementjpa.controller;

import com.sparta.schedulemanagementjpa.entity.Schedule;
import com.sparta.schedulemanagementjpa.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 이 클래스가 REST API 요청을 처리하는 컨트롤러임을 명시
@RequestMapping("/api/schedules") // 모든 일정과 관련된 요청은 /api/schedules로 시작
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 페이징 조회 (GET)
    @GetMapping("/paged")
    public ResponseEntity<Page<Schedule>> getPagedSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Schedule> schedules = scheduleService.getPagedSchedules(page, size);
        return ResponseEntity.ok(schedules);
    }

    // 일정 생성 (POST)
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.ok(createdSchedule);
    }

    // 모든 일정 조회 (GET)
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    // 특정 일정 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    // 일정 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule scheduleDetails) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, scheduleDetails);
        return ResponseEntity.ok(updatedSchedule);
    }

    // 일정 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build(); // 삭제 성공 시 204 No Content 응답
    }
}
