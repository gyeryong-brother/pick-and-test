package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        Long memberId,
        List<CommentResponse> commentResponses,
        String title,
        String content,
        LocalDateTime time
) {

    public static PostResponse from(Post post) {
        List<CommentResponse> commentResponses1 = post.getComments().stream()
                .map(CommentResponse::from)
                .toList();
        return new PostResponse(post.getId(),
                post.getMemberId(),
                commentResponses1,
                post.getTitle(),
                post.getContent(),
                post.getTime()
        );
    }
}
