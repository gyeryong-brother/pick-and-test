package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;


import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        Long memberId,
        Long postId,
        String content,
        LocalDateTime time
) {

    public static CommentResponse from(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getMemberId(),
                comment.getPostId(),
                comment.getContent(),
                comment.getTime()
        );
    }
}
