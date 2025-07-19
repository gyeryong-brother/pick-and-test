package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.QCommentEntity.commentEntity;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.CommentEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.QCommentEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper.CommentDataAccessMapper;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository.CommentJpaRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.provider.QueryComment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final CommentDataAccessMapper commentDataAccessMapper;
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        List<CommentEntity> commentEntities=queryFactory.selectFrom(commentEntity)
                .where(commentEntity.postEntity.id.eq(postId))
                .fetch();
        return commentEntities.stream()
                .map(commentDataAccessMapper::commentEntityToComment)
                .toList();
    }

    @Override
    public Comment findById(Long id) {
        Optional<CommentEntity> commentEntity1=commentJpaRepository.findById(id);
        return commentDataAccessMapper.commentEntityToComment(
                commentEntity1.orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."))
        );
    }
}
