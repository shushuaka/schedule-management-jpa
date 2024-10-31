package com.sparta.schedulemanagementjpa.service;

import com.sparta.schedulemanagementjpa.entity.Comment;
import com.sparta.schedulemanagementjpa.entity.Schedule;
import com.sparta.schedulemanagementjpa.repository.CommentRepository;
import com.sparta.schedulemanagementjpa.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성
    public Comment createComment(Long scheduleId, Comment comment) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.")
        );
        comment.setSchedule(schedule);
        return commentRepository.save(comment);
    }

    // 특정 일정에 달린 모든 댓글 조회
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.")
        );
        return schedule.getComments();
    }

    // 댓글 수정
    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        comment.setContent(commentDetails.getContent());
        comment.setModifiedAt(commentDetails.getModifiedAt());

        //save() 제거, 더티체킹으로 자동 업데이트
        return comment;
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        commentRepository.delete(comment);
    }
}
