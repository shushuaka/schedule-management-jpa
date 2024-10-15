package com.sparta.schedulemanagementjpa.controller;

import com.sparta.schedulemanagementjpa.entity.Comment;
import com.sparta.schedulemanagementjpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성 (POST)
    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable Long scheduleId, @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(scheduleId, comment);
        return ResponseEntity.ok(createdComment);
    }

    // 특정 일정의 댓글 조회 (GET)
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByScheduleId(@PathVariable Long scheduleId) {
        List<Comment> comments = commentService.getCommentsByScheduleId(scheduleId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        Comment updatedComment = commentService.updateComment(id, commentDetails);
        return ResponseEntity.ok(updatedComment);
    }

    // 댓글 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
