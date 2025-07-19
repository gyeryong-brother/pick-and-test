package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import java.util.List;

public interface PostQueryRepository {
    List<Post> findAll();

    Post findById(Long id);
}
