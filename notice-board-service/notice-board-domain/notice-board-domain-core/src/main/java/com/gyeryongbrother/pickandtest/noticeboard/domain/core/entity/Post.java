package com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Post {

    private final Long id;
    private final Long memberId;
    private final String title;
    private final String content;
    private final LocalDateTime time;
    private final List<Comment> comments;

    public List<Comment> getComments(){
        if(comments==null){
            return List.of();
        }
        return comments;
    }
}
