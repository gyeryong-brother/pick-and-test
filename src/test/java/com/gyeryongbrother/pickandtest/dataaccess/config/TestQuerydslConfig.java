package com.gyeryongbrother.pickandtest.dataaccess.config;

import com.gyeryongbrother.pickandtest.dataaccess.adapter.StockQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestQuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public StockDataAccessMapper stockDataAccessMapper() {
        return new StockDataAccessMapper();
    }

    @Bean
    public StockQueryRepository stockQueryRepository() {
        return new StockQueryRepositoryImpl(queryFactory(), stockDataAccessMapper());
    }
}
