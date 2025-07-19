package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.DeleteCommentCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.DeletePostCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.DeletePostRequest;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WriteCommentCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WritePostCommand;

public interface NoticeboardService {

    PostResponse writePost(WritePostCommand writePostCommand);

    PostResponse writeComment(WriteCommentCommand writeCommentCommand);

    void deletePost(DeletePostCommand deletePostCommand);

    PostResponse deleteComment(DeleteCommentCommand deleteCommentCommand);
}
