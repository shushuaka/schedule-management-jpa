package com.sparta.schedulemanagementjpa.controller;

import com.sparta.schedulemanagementjpa.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementjpa.dto.ScheduleResponseDto;
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
    public ResponseEntity<Page<ScheduleResponseDto>> getPagedSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ScheduleResponseDto> schedules = scheduleService.getPagedSchedules(page, size);
        return ResponseEntity.ok(schedules);
    }

    // 일정 생성 (POST)
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto createdSchedule = scheduleService.createSchedule(scheduleRequestDto);
        return ResponseEntity.ok(createdSchedule);
    }

    // 모든 일정 조회 (GET)
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    // 특정 일정 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    // 일정 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(id, scheduleRequestDto);
        return ResponseEntity.ok(updatedSchedule);
    }

    // 일정 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build(); // 삭제 성공 시 204 No Content 응답
    }
}
