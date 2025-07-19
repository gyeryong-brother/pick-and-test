package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record DeletePostRequest(
        Long postId
) {

    public DeletePostCommand toCommand(){
        return new DeletePostCommand(postId);
    }
}
