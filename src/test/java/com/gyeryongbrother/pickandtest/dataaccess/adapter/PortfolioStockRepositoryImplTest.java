package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("포트폴리오주식 리퍼지토리를 구현한다")
public class PortfolioStockRepositoryImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PortfolioStockRepository portfolioStockRepository;

    @Test
    @DisplayName("포트폴리오주식을 저장한다")
    void save(){
        //given
        Stock apple= StockFixture.apple();
        Stock savedApple=stockRepository.save(apple);

        PortfolioStock appleInPortfolio= PortfolioStock.builder().stock(savedApple).portion(BigDecimal.valueOf(0.5)).build();

        PortfolioStock expected=appleInPortfolio;

        //when
        PortfolioStock result= portfolioStockRepository.save(appleInPortfolio);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
