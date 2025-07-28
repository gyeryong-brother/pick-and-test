package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.config;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter.CommentQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter.CommentRepositoryImpl;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter.PostQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter.PostRepositoryImpl;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper.CommentDataAccessMapper;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper.PostDataAccessMapper;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository.CommentJpaRepository;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository.PostJpaRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestQuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CommentJpaRepository commentJpaRepository;

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public CommentDataAccessMapper commentDataAccessMapper() {
        return new CommentDataAccessMapper();
    }

    @Bean
    public PostDataAccessMapper postDataAccessMapper() {
        return new PostDataAccessMapper(commentDataAccessMapper());
    }

    @Bean
    public CommentQueryRepository commentQueryRepository() {
        return new CommentQueryRepositoryImpl(
                queryFactory(),
                commentDataAccessMapper(),
                commentJpaRepository
        );
    }

    @Bean
    public CommentRepository commentRepository() {
        return new CommentRepositoryImpl(
                commentJpaRepository,
                commentDataAccessMapper(),
                queryFactory()
        );
    }

    @Bean
    public PostQueryRepository postQueryRepository() {
        return new PostQueryRepositoryImpl(
                postJpaRepository,
                postDataAccessMapper()
        );
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepositoryImpl(
                postJpaRepository,
                postDataAccessMapper()
        );
    }
}
