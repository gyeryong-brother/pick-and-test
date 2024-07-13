package com.gyeryongbrother.pickandtest.dataaccess.config;

import com.gyeryongbrother.pickandtest.dataaccess.adapter.DividendRepositoryImpl;
import com.gyeryongbrother.pickandtest.dataaccess.adapter.StockQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.dataaccess.adapter.StockRepositoryImpl;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
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
    private StockJpaRepository stockJpaRepository;

    @Autowired
    private DividendJpaRepository dividendJpaRepository;

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

    @Bean
    public StockRepository stockRepository() {
        return new StockRepositoryImpl(stockJpaRepository, stockDataAccessMapper());
    }

    @Bean
    public DividendRepository dividendRepository() {
        return new DividendRepositoryImpl(dividendJpaRepository, stockDataAccessMapper());
    }
}
