package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import java.time.LocalDateTime;

public record WriteCommentCommand(
        Long memberId,
        Long postId,
        String content
) {

    public Comment toDomain(){
        return Comment.builder()
                .memberId(memberId)
                .postId(postId)
                .content(content)
                .build();
    }
}
