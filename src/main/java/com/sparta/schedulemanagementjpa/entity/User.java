package com.sparta.schedulemanagementjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "유저명은 필수 입력 값입니다.")
    @Size(max = 20, message = "유저명은 최대 20자까지 입력할 수 있습니다.")
    @Column(nullable = false, unique = true)
    private String userName; // 유저명

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @Column(nullable = false, unique = true)
    private String email; // 이메일

    @CreatedDate // 생성 일자
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정 일자
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    // 유저와 일정의 관계는 양방향 설정 (일정을 작성한 유저 정보가 일정에도 저장됨)
}
