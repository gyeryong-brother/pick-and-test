package com.gyeryongbrother.pickandtest.stockprice.dataaccess.config;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter.StockPriceQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter.StockPriceRepositoryImpl;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.mapper.StockPriceDataAccessMapper;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class TestQuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StockPriceJpaRepository stockPriceJpaRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public StockPriceDataAccessMapper stockPriceDataAccessMapper() {
        return new StockPriceDataAccessMapper();
    }

    @Bean
    public StockPriceRepository stockPriceRepository() {
        return new StockPriceRepositoryImpl(stockPriceJpaRepository, stockPriceDataAccessMapper(), namedParameterJdbcTemplate);
    }

    @Bean
    public StockPriceQueryRepository stockPriceQueryRepository() {
        return new StockPriceQueryRepositoryImpl(queryFactory());
    }
}
