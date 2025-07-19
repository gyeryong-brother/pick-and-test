package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.CommentResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WriteCommentCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WritePostCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input.NoticeboardService;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeboardServiceImpl implements NoticeboardService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostQueryRepository postQueryRepository;

    @Override
    public PostResponse writePost(WritePostCommand writePostCommand) {
        Post post=writePostCommand.toDomain();
        Post saved=postRepository.save(post);
        return PostResponse.from(saved);
    }

    @Override
    public PostResponse writeComment(WriteCommentCommand writeCommentCommand) {
        Comment comment=writeCommentCommand.toDomain();
        Comment saved=commentRepository.save(comment);
        Post post=postQueryRepository.findById(saved.getPostId());
        return PostResponse.from(post);
    }
}
