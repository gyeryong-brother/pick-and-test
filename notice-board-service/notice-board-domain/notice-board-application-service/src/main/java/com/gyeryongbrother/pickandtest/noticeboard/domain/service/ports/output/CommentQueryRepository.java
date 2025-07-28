package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import java.util.List;

public interface CommentQueryRepository {
    List<Comment> findAllByPostId(Long postId);

    Comment findById(Long id);
}
