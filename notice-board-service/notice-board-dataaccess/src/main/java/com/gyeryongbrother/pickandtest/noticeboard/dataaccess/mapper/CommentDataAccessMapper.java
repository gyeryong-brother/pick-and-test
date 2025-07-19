package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.CommentEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.PostEntity;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDataAccessMapper {

    public CommentEntity commentToCommentEntity(Comment comment){
        PostEntity postEntity=PostEntity.builder()
                .id(comment.getPostId())
                .build();

        return CommentEntity.builder()
                .id(comment.getId())
                .postEntity(postEntity)
                .memberId(comment.getMemberId())
                .content(comment.getContent())
                .time(comment.getTime())
                .build();
    }

    public Comment commentEntityToComment(CommentEntity commentEntity){
        return Comment.builder()
                .id(commentEntity.getId())
                .postId(commentEntity.getPostEntity().getId())
                .memberId(commentEntity.getMemberId())
                .content(commentEntity.getContent())
                .time(commentEntity.getTime())
                .build();
    }
}
