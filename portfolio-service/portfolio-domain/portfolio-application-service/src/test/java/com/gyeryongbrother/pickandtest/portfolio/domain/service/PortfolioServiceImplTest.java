package com.gyeryongbrother.pickandtest.portfolio.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioStockCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioQueryRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
@DisplayName("포트폴리오 서비스를 구현한다")
public class PortfolioServiceImplTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private PortfolioStockRepository portfolioStockRepository;

    @Mock
    private PortfolioQueryRepository portfolioQueryRepository;

    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() {
        portfolioService = new PortfolioServiceImpl(
                portfolioRepository,
                portfolioStockRepository,
                portfolioQueryRepository
        );
    }

    @Test
    @DisplayName("포트폴리오 업데이트 기능을 구현한다")
    void updatePortfolio() {
        //given
        PortfolioStock apple = new PortfolioStock(null, null, 1L, BigDecimal.valueOf(0.5));
        PortfolioStock nvidia = new PortfolioStock(null, null, 2L, BigDecimal.valueOf(0.5));
        PortfolioStock google = new PortfolioStock(null, null, 3L, BigDecimal.valueOf(0.5));
        PortfolioStock microsoft = new PortfolioStock(null, null, 4L, BigDecimal.valueOf(0.5));

        Portfolio initial = new Portfolio(null, 1L, List.of(apple, nvidia));
        Portfolio saved = portfolioRepository.save(initial);

        UpdatePortfolioStockCommand updatePortfolioStockCommand1 = new UpdatePortfolioStockCommand(3L,
                BigDecimal.valueOf(0.5));
        UpdatePortfolioStockCommand updatePortfolioStockCommand2 = new UpdatePortfolioStockCommand(4L,
                BigDecimal.valueOf(0.5));
        UpdatePortfolioCommand updatePortfolioCommand = new UpdatePortfolioCommand(
                List.of(updatePortfolioStockCommand1, updatePortfolioStockCommand2),
                1L
        );

        UpdatePortfolioResponse expected = UpdatePortfolioResponse.from(updatePortfolioCommand.toDomain(1L));

        Mockito.when(portfolioQueryRepository.findById(any())).thenReturn(updatePortfolioCommand.toDomain(1L));

        //when
        UpdatePortfolioResponse result = portfolioService.updatePortfolio(updatePortfolioCommand);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
