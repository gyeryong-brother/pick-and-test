package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import java.time.LocalDateTime;

public record SimplePostResponse(
        Long postId,
        Long memberId,
        String title,
        LocalDateTime time
) {

    public static SimplePostResponse from(Post post){
        return new SimplePostResponse(post.getId(),post.getMemberId(),post.getTitle(),post.getTime());
    }
}
