package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WriteCommentCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WritePostCommand;

public interface NoticeboardService {

    PostResponse writePost(WritePostCommand writePostCommand);

    PostResponse writeComment(WriteCommentCommand writeCommentCommand);
}
