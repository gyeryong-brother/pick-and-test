package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.CommentEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.PostEntity;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDataAccessMapper {

    private final CommentDataAccessMapper commentDataAccessMapper;

    public PostEntity postToPostEntity(Post post){
        List<CommentEntity> commentEntities=post.getComments().stream()
                .map(commentDataAccessMapper::commentToCommentEntity)
                .toList();
        return PostEntity.builder()
                .id(post.getId())
                .comments(commentEntities)
                .title(post.getTitle())
                .content(post.getContent())
                .memberId(post.getMemberId())
                .time(post.getTime())
                .build();
    }

    public Post postEntityToPost(PostEntity postEntity){
        List<Comment> comments=postEntity.getComments().stream()
                .map(commentDataAccessMapper::commentEntityToComment)
                .toList();
        return Post.builder()
                .id(postEntity.getId())
                .memberId(postEntity.getMemberId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .comments(comments)
                .time(postEntity.getTime())
                .build();
    }
}
