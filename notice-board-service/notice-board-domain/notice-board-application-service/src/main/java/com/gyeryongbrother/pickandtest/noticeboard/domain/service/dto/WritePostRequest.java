package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record WritePostRequest(
        Long memberId,
        String title,
        String content
) {

    public WritePostCommand toCommand(){
        return new WritePostCommand(memberId,title,content);
    }
}
