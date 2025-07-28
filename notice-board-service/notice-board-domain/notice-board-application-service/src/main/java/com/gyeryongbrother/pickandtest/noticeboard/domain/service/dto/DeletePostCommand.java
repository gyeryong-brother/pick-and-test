package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

public record DeletePostCommand(
        Long memberId,
        Long postId
) {
}
