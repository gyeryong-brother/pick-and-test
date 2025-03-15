package com.gyeryongbrother.pickandtest.stock.dataaccess.config;

import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.DividendQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.DividendRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.FavoriteStockQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.FavoriteStockRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.IncomeStatementQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.IncomeStatementRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.StockDetailRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.StockQueryRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.StockRepositoryImpl;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.FavoriteStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.IncomeStatementDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.StockPriceDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.FavoriteStockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.IncomeStatementJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockDetailJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.DividendQueryServiceImpl;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.DividendQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.DividendRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockDetailRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
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
    private StockDetailJpaRepository stockDetailJpaRepository;

    @Autowired
    private DividendJpaRepository dividendJpaRepository;

    @Autowired
    private IncomeStatementJpaRepository incomeStatementJpaRepository;

    @Autowired
    private FavoriteStockJpaRepository favoriteStockJpaRepository;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public DividendDataAccessMapper dividendDataAccessMapper() {
        return new DividendDataAccessMapper();
    }

    @Bean
    public StockPriceDataAccessMapper stockPriceDataAccessMapper() {
        return new StockPriceDataAccessMapper();
    }

    @Bean
    public FavoriteStockDataAccessMapper favoriteStockDataAccessMapper() {
        return new FavoriteStockDataAccessMapper(stockDataAccessMapper());
    }

    @Bean
    public StockDataAccessMapper stockDataAccessMapper() {
        return new StockDataAccessMapper();
    }

    @Bean
    public IncomeStatementDataAccessMapper incomeStatementDataAccessMapper() {
        return new IncomeStatementDataAccessMapper(stockDataAccessMapper());
    }

    @Bean
    public StockQueryRepository stockQueryRepository() {
        return new StockQueryRepositoryImpl(queryFactory(), stockDataAccessMapper());
    }

    @Bean
    public DividendQueryRepository dividendQueryRepository() {
        return new DividendQueryRepositoryImpl(queryFactory(), dividendDataAccessMapper());
    }

    @Bean
    public FavoriteStockQueryRepository favoriteStockQueryRepository() {
        return new FavoriteStockQueryRepositoryImpl(queryFactory(), favoriteStockDataAccessMapper());
    }

    @Bean
    public FavoriteStockRepository favoriteStockRepository() {
        return new FavoriteStockRepositoryImpl(favoriteStockJpaRepository, favoriteStockDataAccessMapper());
    }

    @Bean
    public StockRepository stockRepository() {
        return new StockRepositoryImpl(stockJpaRepository, stockDataAccessMapper());
    }

    @Bean
    public StockDetailRepository stockDetailRepository() {
        return new StockDetailRepositoryImpl(stockDetailJpaRepository);
    }

    @Bean
    public DividendRepository dividendRepository() {
        return new DividendRepositoryImpl(dividendJpaRepository, dividendDataAccessMapper());
    }

    @Bean
    public DividendQueryService dividendQueryService() {
        return new DividendQueryServiceImpl(dividendQueryRepository());
    }

    @Bean
    public IncomeStatementRepository incomeStatementRepository() {
        return new IncomeStatementRepositoryImpl(incomeStatementJpaRepository, incomeStatementDataAccessMapper());
    }

    @Bean
    public IncomeStatementQueryRepository incomeStatementQueryRepository() {
        return new IncomeStatementQueryRepositoryImpl(queryFactory(), incomeStatementDataAccessMapper());
    }
}
