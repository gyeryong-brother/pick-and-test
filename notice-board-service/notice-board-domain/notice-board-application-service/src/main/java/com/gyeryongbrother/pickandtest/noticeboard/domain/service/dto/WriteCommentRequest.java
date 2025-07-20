package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record WriteCommentRequest(
        String content
) {

    public WriteCommentCommand toCommand(Long memberId, Long postId) {
        return new WriteCommentCommand(memberId, postId, content);
    }
}
