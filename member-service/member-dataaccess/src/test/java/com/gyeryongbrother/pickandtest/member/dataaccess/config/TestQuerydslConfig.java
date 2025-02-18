package com.gyeryongbrother.pickandtest.member.dataaccess.config;

import com.gyeryongbrother.pickandtest.member.dataaccess.adapter.MemberRepositoryImpl;
import com.gyeryongbrother.pickandtest.member.dataaccess.mapper.MemberDataAccessMapper;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.MemberJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.JwtUtil;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
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
    private MemberJpaRepository memberJpaRepository;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(memberJpaRepository, memberDataAccessMapper());
    }
    @Bean
    public MemberDataAccessMapper memberDataAccessMapper() {
        return new MemberDataAccessMapper();
    }
}
