package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioRepository;
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
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    @Autowired
    private PortfolioStockJpaRepository portfolioStockJpaRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("포트폴리오주식을 저장한다")
    void save() {
        //given
        Stock apple = StockFixture.apple();
        Stock savedApple = stockRepository.save(apple);

        PortfolioStock appleInPortfolio = PortfolioStock.builder().stock(savedApple).portion(BigDecimal.valueOf(0.5))
                .build();

        PortfolioStock expected = appleInPortfolio;

        //when
        PortfolioStock result = portfolioStockRepository.save(appleInPortfolio);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("포트폴리오 아이디를 통해 포트폴리오 주식 삭제")
    void deleteAllByPortfolioId(){
        //given
        Stock apple=StockFixture.apple();
        Stock nvidia=StockFixture.nvidia();
        Stock microsoft=StockFixture.microsoft();

        Stock savedApple=stockRepository.save(apple);
        Stock savedNvidia=stockRepository.save(nvidia);
        Stock savedMicrosoft=stockRepository.save(microsoft);

        Member member=Member.builder().build();

        Member savedMember=memberRepository.save(member);

        Portfolio portfolio1=Portfolio.builder().memberId(savedMember.getId()).build();
        Portfolio portfolio2=Portfolio.builder().memberId(savedMember.getId()).build();

        Portfolio savedPortfolio1=portfolioRepository.save(portfolio1);
        Portfolio savedPortfolio2=portfolioRepository.save(portfolio2);

        PortfolioStock applePortfolioStock=PortfolioStock.builder()
                .portfolioId(savedPortfolio1.getId())
                .stock(savedApple)
                .portion(BigDecimal.valueOf(0.5))
                .build();
        PortfolioStock nvidiaPortfolioStock=PortfolioStock.builder()
                .portfolioId(savedPortfolio2.getId())
                .stock(savedNvidia)
                .portion(BigDecimal.valueOf(1.0))
                .build();
        PortfolioStock microsoftPortfolioStock=PortfolioStock.builder()
                .portfolioId(savedPortfolio1.getId())
                .stock(savedMicrosoft)
                .portion(BigDecimal.valueOf(0.5))
                .build();

        PortfolioStock savedApplePortfolioStock=portfolioStockRepository.save(applePortfolioStock);
        PortfolioStock savedNvidiaPortfolioStock=portfolioStockRepository.save(nvidiaPortfolioStock);
        PortfolioStock savedMicrosoftPortfolioStock=portfolioStockRepository.save(microsoftPortfolioStock);

        portfolioStockRepository.deleteAllByPortfolioId(savedPortfolio1.getId());

        List<PortfolioStockEntity> expected=List.of(
                portfolioStockDataAccessMapper.portfolioStockToPortfolioStockEntity(savedNvidiaPortfolioStock)
        );

        //when
        List<PortfolioStockEntity> result=portfolioStockJpaRepository.findAll();

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("포트폴리오 아이디를 통해 포트폴리오 주식 삭제-삭제 할 내용이 없을 경우")
    void deleteAllByPortfolioIdWhenNone(){
        //given
        Stock apple=StockFixture.apple();
        Stock nvidia=StockFixture.nvidia();
        Stock microsoft=StockFixture.microsoft();

        Stock savedApple=stockRepository.save(apple);
        Stock savedNvidia=stockRepository.save(nvidia);
        Stock savedMicrosoft=stockRepository.save(microsoft);

        Member member=Member.builder().build();

        Member savedMember=memberRepository.save(member);

        Portfolio portfolio1=Portfolio.builder().memberId(savedMember.getId()).build();
        Portfolio portfolio2=Portfolio.builder().memberId(savedMember.getId()).build();

        Portfolio savedPortfolio1=portfolioRepository.save(portfolio1);
        Portfolio savedPortfolio2=portfolioRepository.save(portfolio2);

        PortfolioStock applePortfolioStock=PortfolioStock.builder()
                .portfolioId(savedPortfolio1.getId())
                .stock(savedApple)
                .portion(BigDecimal.valueOf(0.5))
                .build();
        PortfolioStock nvidiaPortfolioStock=PortfolioStock.builder()
                .portfolioId(savedPortfolio2.getId())
                .stock(savedNvidia)
                .portion(BigDecimal.valueOf(1.0))
                .build();
        PortfolioStock microsoftPortfolioStock=PortfolioStock.builder()
                .portfolioId(savedPortfolio1.getId())
                .stock(savedMicrosoft)
                .portion(BigDecimal.valueOf(0.5))
                .build();

        PortfolioStock savedApplePortfolioStock=portfolioStockRepository.save(applePortfolioStock);
        //PortfolioStock savedNvidiaPortfolioStock=portfolioStockRepository.save(nvidiaPortfolioStock);
        PortfolioStock savedMicrosoftPortfolioStock=portfolioStockRepository.save(microsoftPortfolioStock);

        portfolioStockRepository.deleteAllByPortfolioId(savedPortfolio2.getId());

        List<PortfolioStockEntity> expected=List.of(
                portfolioStockDataAccessMapper.portfolioStockToPortfolioStockEntity(savedApplePortfolioStock),
                portfolioStockDataAccessMapper.portfolioStockToPortfolioStockEntity(savedMicrosoftPortfolioStock)
        );

        //when
        List<PortfolioStockEntity> result=portfolioStockJpaRepository.findAll();

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}







