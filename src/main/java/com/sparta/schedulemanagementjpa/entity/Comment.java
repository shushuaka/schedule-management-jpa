package com.sparta.schedulemanagementjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity // 이 클래스가 데이터베이스 테이블과 매핑된다는 것을 의미
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id // 이 필드를 기본 키로 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략 설정 (자동 증가)
    private Long id;

    @ManyToOne // 한 일정에 여러 댓글이 달릴 수 있는 관계를 정의 (N:1 관계)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private String userName; // 작성 유저명

    @Column(nullable = false)
    private String content; // 댓글 내용

    @CreatedDate // 생성 일자
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정 일자
    private LocalDateTime modifiedAt;
}
