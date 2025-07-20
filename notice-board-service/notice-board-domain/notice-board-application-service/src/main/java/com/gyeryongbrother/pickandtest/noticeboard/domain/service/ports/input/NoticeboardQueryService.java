package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostsResponse;

public interface NoticeboardQueryService {

    PostsResponse findAllPosts();

    PostResponse findPostById(Long postId);
}
