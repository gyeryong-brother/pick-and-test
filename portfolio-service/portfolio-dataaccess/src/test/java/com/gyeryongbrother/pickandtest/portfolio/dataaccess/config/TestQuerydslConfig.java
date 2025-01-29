package com.gyeryongbrother.pickandtest.portfolio.dataaccess.config;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter.PortfolioQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter.PortfolioRepositoryImpl;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter.PortfolioStockQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter.PortfolioStockRepositoryImpl;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.PortfolioServiceImpl;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioQueryRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockQueryRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
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
    private PortfolioJpaRepository portfolioJpaRepository;

    @Autowired
    private PortfolioStockJpaRepository portfolioStockJpaRepository;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public PortfolioDataAccessMapper portfolioDataAccessMapper() {
        return new PortfolioDataAccessMapper(portfolioStockDataAccessMapper());
    }

    @Bean
    public PortfolioStockDataAccessMapper portfolioStockDataAccessMapper() {
        return new PortfolioStockDataAccessMapper();
    }

    @Bean
    public PortfolioQueryRepository portfolioQueryRepository() {
        return new PortfolioQueryRepositoryImpl(queryFactory(), portfolioDataAccessMapper());
    }

    @Bean
    public PortfolioRepository portfolioRepository() {
        return new PortfolioRepositoryImpl(portfolioDataAccessMapper(), portfolioJpaRepository);
    }

    @Bean
    public PortfolioStockQueryRepository portfolioStockQueryRepository() {
        return new PortfolioStockQueryRepositoryImpl(queryFactory(), portfolioStockDataAccessMapper());
    }

    @Bean
    public PortfolioStockRepository portfolioStockRepository() {
        return new PortfolioStockRepositoryImpl(
                portfolioStockJpaRepository,
                portfolioStockDataAccessMapper(),
                portfolioJpaRepository
        );
    }

    @Bean
    public PortfolioService portfolioService() {
        return new PortfolioServiceImpl(portfolioRepository(), portfolioStockRepository());
    }
}
