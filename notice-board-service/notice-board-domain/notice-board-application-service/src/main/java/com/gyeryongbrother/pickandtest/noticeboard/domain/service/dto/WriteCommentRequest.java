package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record WriteCommentRequest(
        Long memberId,
        Long postId,
        String content
) {

    public WriteCommentCommand toCommand(){
        return new WriteCommentCommand(memberId,postId,content);
    }
}
