package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.PostEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper.PostDataAccessMapper;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository.PostJpaRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final PostJpaRepository postJpaRepository;
    private final PostDataAccessMapper postDataAccessMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> findAll() {
        List<PostEntity> postEntities=postJpaRepository.findAll();
        return postEntities.stream()
                .map(postDataAccessMapper::postEntityToPost)
                .toList();
    }
}
