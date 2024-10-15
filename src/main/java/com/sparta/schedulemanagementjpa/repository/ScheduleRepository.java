package com.sparta.schedulemanagementjpa.repository;

import com.sparta.schedulemanagementjpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // JpaRepository를 상속받아 기본적인 CRUD 기능 제공
}
