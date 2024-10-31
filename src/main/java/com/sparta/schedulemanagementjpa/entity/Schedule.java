package com.sparta.schedulemanagementjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Schedule {

    @Id // 이 필드를 기본 키로 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략 설정 (자동 증가)
    private Long id;

    @Column(nullable = false)
    private String userName; // 작성 유저명


    @Column(nullable = false)
    private String title; // 할일 제목


    @Column(nullable = false)
    private String content; // 할일 내용

    @CreatedDate // 생성 일자
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정 일자
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now(); // 기본값 설정 - 현재 날짜 및 시간

    // 댓글 목록 (OneToMany 관계)
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 작성 유저 (ManyToOne 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 일정 작성 유저

    // 생성자, 수정자 메서드는 Lombok에서 제공하는 @Getter, @Setter를 통해 자동 생성
}
