package com.sparta.schedulemanagementjpa.repository;

import com.sparta.schedulemanagementjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository를 상속받아 기본적인 CRUD 기능 제공
}
