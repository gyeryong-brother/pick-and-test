package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record DeleteCommentCommand(
        Long id,
        Long postId,
        Long memberId
) {
}
