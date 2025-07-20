package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.DeleteCommentCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.DeletePostCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostsResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.SimplePostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WriteCommentCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WritePostCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception.NoticeboardServiceException;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception.NoticeboardServiceExceptionType;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input.NoticeboardService;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeboardServiceImpl implements NoticeboardService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostQueryRepository postQueryRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Override
    public PostResponse writePost(WritePostCommand writePostCommand) {
        Post post = writePostCommand.toDomain();
        Post saved = postRepository.save(post);
        return PostResponse.from(saved);
    }

    @Override
    public PostResponse writeComment(WriteCommentCommand writeCommentCommand) {
        Comment comment = writeCommentCommand.toDomain();
        Comment saved = commentRepository.save(comment);
        Post post = postQueryRepository.findById(saved.getPostId());
        return PostResponse.from(post);
    }

    @Override
    public PostsResponse deletePost(DeletePostCommand deletePostCommand) {
        Long postId = deletePostCommand.postId();
        Post post = postQueryRepository.findById(postId);
        if (post.getMemberId() != deletePostCommand.memberId()) {
            throw new NoticeboardServiceException(NoticeboardServiceExceptionType.CAN_NOT_DELETE_POST);
        }
        postRepository.deleteById(postId);
        List<Post> remainPosts = postQueryRepository.findAll();
        List<SimplePostResponse> remainSimplePosts = remainPosts.stream()
                .map(SimplePostResponse::from)
                .toList();
        return new PostsResponse(remainSimplePosts);
    }

    @Override
    public PostResponse deleteComment(DeleteCommentCommand deleteCommentCommand) {
        Long commentId = deleteCommentCommand.id();
        Comment comment = commentQueryRepository.findById(commentId);
        if (comment.getMemberId() != deleteCommentCommand.memberId()) {
            throw new NoticeboardServiceException(NoticeboardServiceExceptionType.CAN_NOT_DELETE_COMMENT);
        }
        commentRepository.deleteById(commentId);
        Post post = postQueryRepository.findById(deleteCommentCommand.postId());
        return PostResponse.from(post);
    }
}
