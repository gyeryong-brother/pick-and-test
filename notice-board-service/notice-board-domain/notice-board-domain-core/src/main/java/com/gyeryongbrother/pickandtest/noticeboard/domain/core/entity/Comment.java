package com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Comment {

    private final Long id;
    private final Long memberId;
    private final Long postId;
    private final String content;
    private final LocalDateTime time;
}
