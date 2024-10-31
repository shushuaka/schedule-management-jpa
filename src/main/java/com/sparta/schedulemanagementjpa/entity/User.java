package com.sparta.schedulemanagementjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // 이 클래스가 데이터베이스 테이블과 매핑된다는 것을 의미
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id // 이 필드를 기본 키로 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략 설정 (자동 증가)
    private Long id;


    @Column(nullable = false, unique = true)
    private String userName; // 유저명


    @Column(nullable = false, unique = true)
    private String email; // 이메일

    @CreatedDate // 생성 일자
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정 일자
    private LocalDateTime modifiedAt;

    // UserSchedule과 관계 설정 (지연 로딩 적용)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSchedule> userSchedules = new ArrayList<>();

}
