package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;

public interface PostRepository {

    Post save(Post post);

    void deleteById(Long id);
}
