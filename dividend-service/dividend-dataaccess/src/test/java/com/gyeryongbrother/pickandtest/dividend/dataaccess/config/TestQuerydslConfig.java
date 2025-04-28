package com.gyeryongbrother.pickandtest.dividend.dataaccess.config;

import com.gyeryongbrother.pickandtest.dividend.dataaccess.adapter.DividendRepositoryImpl;
import com.gyeryongbrother.pickandtest.dividend.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.dividend.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendRepository;
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
    private DividendJpaRepository dividendJpaRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Bean
    public DividendDataAccessMapper dividendDataAccessMapper() {
        return new DividendDataAccessMapper();
    }

    @Bean
    public DividendRepository dividendRepository() {
        return new DividendRepositoryImpl(
                dividendJpaRepository,
                dividendDataAccessMapper(),
                namedParameterJdbcTemplate
        );
    }
}
