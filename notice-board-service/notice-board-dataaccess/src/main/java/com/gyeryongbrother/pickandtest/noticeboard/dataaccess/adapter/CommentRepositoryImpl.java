package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.QCommentEntity.commentEntity;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.CommentEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.QCommentEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper.CommentDataAccessMapper;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository.CommentJpaRepository;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository.PostJpaRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentDataAccessMapper commentDataAccessMapper;
    private final JPAQueryFactory queryFactory;
    private final PostJpaRepository postJpaRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity=commentDataAccessMapper.commentToCommentEntity(comment);
        CommentEntity saved=commentJpaRepository.save(commentEntity);
        return commentDataAccessMapper.commentEntityToComment(saved);
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        queryFactory.delete(commentEntity)
                .where(commentEntity.postEntity.id.eq(postId))
                .execute();
    }

    @Override
    public void deleteById(Long id) {
        commentJpaRepository.deleteById(id);
    }
}
