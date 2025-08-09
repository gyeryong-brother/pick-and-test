package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockQueryRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("포트폴리오주식 조회 리퍼지터리 구현")
class PortfolioStockQueryRepositoryImplTest {

    @Autowired
    private PortfolioStockQueryRepository portfolioStockQueryRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioStockJpaRepository portfolioStockJpaRepository;

    @Autowired
    private PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    @Test
    @DisplayName("포트폴리오 아이디를 사용하여 해당 포트폴리오에 들어있는 모든 포트폴리오주식을 불러옴")
    void findAllByPortfolioId() {
        //given
        Portfolio appleNvidia = Portfolio.builder()
                .memberId(1L)
                .build();
        Portfolio savedPortfolio = portfolioRepository.save(appleNvidia);

        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStock.builder().stockId(1L).portfolioId(savedPortfolio.getId())
                        .portion(BigDecimal.valueOf(0.5)).build(),
                PortfolioStock.builder().stockId(2L).portfolioId(savedPortfolio.getId())
                        .portion(BigDecimal.valueOf(0.5)).build()
        );
        portfolioStockJpaRepository.saveAll(portfolioStocks.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockToPortfolioStockEntity)
                .toList()
        );

        //when
        List<PortfolioStock> result = portfolioStockQueryRepository.findAllByPortfolioId(savedPortfolio.getId());

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(portfolioStocks);
    }
}
