package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import java.util.List;

public interface NoticeboardQueryService {

    List<PostResponse> findAllPosts();
}
