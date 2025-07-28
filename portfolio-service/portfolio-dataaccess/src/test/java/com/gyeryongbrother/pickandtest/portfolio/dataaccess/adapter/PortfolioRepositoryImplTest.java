package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("포트폴리오 리퍼지토리를 구현한다")
class PortfolioRepositoryImplTest {

    @Autowired
    private PortfolioStockRepository portfolioStockRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private PortfolioJpaRepository portfolioJpaRepository;
    @Autowired
    private PortfolioStockJpaRepository portfolioStockJpaRepository;
    @Autowired
    private PortfolioDataAccessMapper portfolioDataAccessMapper;

    @Test
    @DisplayName("포트폴리오를 저장한다")
    void save() {
        //given
        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStock.builder().stockId(1L).portion(BigDecimal.valueOf(0.5)).build(),
                PortfolioStock.builder().stockId(2L).portion(BigDecimal.valueOf(0.5)).build()
        );
        Portfolio portfolio = Portfolio.builder()
                .memberId(1L)
                .portfolioStocks(portfolioStocks)
                .build();

        //when
        Portfolio result = portfolioRepository.save(portfolio);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(portfolio);
    }

    @Test
    @DisplayName("포트폴리오를 삭제한다")
    void delete() {
        //given
        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStock.builder().stockId(1L).portion(BigDecimal.valueOf(0.5)).build(),
                PortfolioStock.builder().stockId(2L).portion(BigDecimal.valueOf(0.5)).build()
        );
        Portfolio portfolio1 = Portfolio.builder()
                .memberId(1L)
                .portfolioStocks(portfolioStocks)
                .build();

        Portfolio portfolio2=Portfolio.builder()
                .memberId(1L)
                .portfolioStocks(portfolioStocks)
                .build();

        Portfolio savedPortfolio1=portfolioRepository.save(portfolio1);
        Portfolio savedPortfolio2=portfolioRepository.save(portfolio2);

        //when
        portfolioRepository.delete(savedPortfolio1.getId());
        List<Portfolio> result=portfolioJpaRepository.findAll().stream()
                .map(portfolioDataAccessMapper::portfolioEntityToPortfolio)
                .toList();
        List<Portfolio> expected=List.of(savedPortfolio2);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }


}
