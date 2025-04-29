package com.gyeryongbrother.pickandtest.authentication.dataaccess.config;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter.RefreshTokenQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter.RefreshTokenRepositoryImpl;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper.RefreshTokenDataAccessMapper;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.repository.RefreshTokenJpaRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
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
    private RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public RefreshTokenDataAccessMapper refreshTokenDataAccessMapper() {
        return new RefreshTokenDataAccessMapper();
    }

    @Bean
    public RefreshTokenRepository refreshTokenRepository() {
        return new RefreshTokenRepositoryImpl(refreshTokenJpaRepository, refreshTokenDataAccessMapper());
    }

    @Bean
    public RefreshTokenQueryRepository refreshTokenQueryRepository() {
        return new RefreshTokenQueryRepositoryImpl(queryFactory());
    }
}
