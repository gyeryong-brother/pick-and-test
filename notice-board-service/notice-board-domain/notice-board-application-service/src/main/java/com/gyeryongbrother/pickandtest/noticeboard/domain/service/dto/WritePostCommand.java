package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import java.util.List;

public record WritePostCommand(
        Long memberId,
        String title,
        String content
) {

    public Post toDomain() {
        return Post.builder()
                .memberId(memberId)
                .title(title)
                .content(content)
                .comments(List.of())
                .build();
    }
}
