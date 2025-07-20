package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record WritePostRequest(
        String title,
        String content
) {

    public WritePostCommand toCommand(Long memberId) {
        return new WritePostCommand(memberId, title, content);
    }
}
