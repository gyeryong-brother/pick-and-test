package com.gyeryongbrother.pickandtest.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.application.dto.UpdatePortfolioStockRequest;
import com.gyeryongbrother.pickandtest.application.dto.UpdatePortfolioStockRequests;
import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockQueryRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("포트폴리오 서비스를 구현한다")
public class PortfolioServiceImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioStockRepository portfolioStockRepository;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private PortfolioStockQueryRepository portfolioStockQueryRepository;

    @Test
    @DisplayName("포트폴리오의 주식들을 업데이트한다")
    void updatePortfolioStocks() {
        //given
        Stock apple = StockFixture.apple();
        Stock nvidia = StockFixture.nvidia();
        Stock microsoft = StockFixture.microsoft();

        Stock savedApple = stockRepository.save(apple);
        Stock savedNvidia = stockRepository.save(nvidia);
        Stock savedMicrosoft = stockRepository.save(microsoft);

        Member member = Member.builder().build();

        Member savedMember = memberRepository.save(member);

        Portfolio portfolio1 = Portfolio.builder().memberId(savedMember.getId()).build();
        Portfolio portfolio2 = Portfolio.builder().memberId(savedMember.getId()).build();

        Portfolio savedPortfolio1 = portfolioRepository.save(portfolio1);
        Portfolio savedPortfolio2 = portfolioRepository.save(portfolio2);

        PortfolioStock applePortfolioStock = PortfolioStock.builder()
                .portfolioId(savedPortfolio1.getId())
                .stock(savedApple)
                .portion(BigDecimal.valueOf(0.5))
                .build();
        PortfolioStock nvidiaPortfolioStock = PortfolioStock.builder()
                .portfolioId(savedPortfolio2.getId())
                .stock(savedNvidia)
                .portion(BigDecimal.valueOf(1.0))
                .build();
        PortfolioStock microsoftPortfolioStock = PortfolioStock.builder()
                .portfolioId(savedPortfolio1.getId())
                .stock(savedMicrosoft)
                .portion(BigDecimal.valueOf(0.5))
                .build();

        portfolioStockRepository.saveAll(List.of(applePortfolioStock, nvidiaPortfolioStock, microsoftPortfolioStock));

        UpdatePortfolioStockRequest updatePortfolioStockRequest =
                new UpdatePortfolioStockRequest(savedApple.getId(), BigDecimal.valueOf(1.0));
        UpdatePortfolioStockRequests updatePortfolioStockRequests =
                new UpdatePortfolioStockRequests(List.of(updatePortfolioStockRequest));

        List<PortfolioStock> expected = portfolioService.updatePortfolioStocks(savedPortfolio1.getId(),
                updatePortfolioStockRequests);

        //when
        List<PortfolioStock> result = portfolioStockQueryRepository.findAllByPortfolioId(savedPortfolio1.getId());

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
