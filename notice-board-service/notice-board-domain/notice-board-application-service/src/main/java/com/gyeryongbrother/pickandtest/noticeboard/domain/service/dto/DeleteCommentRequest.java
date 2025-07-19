package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record DeleteCommentRequest(
        Long id
) {

    public DeleteCommentCommand toCommand(){
        return new DeleteCommentCommand(id);
    }
}
