package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    void deleteAllByPostId(Long postId);

    void deleteById(Long id);
}
