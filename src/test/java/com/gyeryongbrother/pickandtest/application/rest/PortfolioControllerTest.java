package com.gyeryongbrother.pickandtest.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.application.dto.UpdatePortfolioStockRequest;
import com.gyeryongbrother.pickandtest.application.dto.UpdatePortfolioStockRequests;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("포트폴리오 api 를 제공한다")
@Sql("/truncate.sql")
public class PortfolioControllerTest {

    @Autowired
    PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private PortfolioStockJpaRepository portfolioStockJpaRepository;
    @Autowired
    private PortfolioStockRepository portfolioStockRepository;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("포트폴리오에 포함된 모든 주식을 가져온다")
    void findAllPortfolioStocks() {
        //given
        Stock apple = StockFixture.apple();
        Stock nvidia = StockFixture.nvidia();
        Stock savedApple = stockRepository.save(apple);
        Stock savedNvidia = stockRepository.save(nvidia);

        Member member = Member.builder().build();
        Member savedMember = memberRepository.save(member);

        Portfolio appleNvidia = Portfolio.builder()
                .memberId(savedMember.getId())
                .build();
        Portfolio savedPortfolio = portfolioRepository.save(appleNvidia);

        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStock.builder().stock(savedApple).portfolioId(savedPortfolio.getId())
                        .portion(BigDecimal.valueOf(0.5)).build(),
                PortfolioStock.builder().stock(savedNvidia).portfolioId(savedPortfolio.getId())
                        .portion(BigDecimal.valueOf(0.5)).build()
        );
        portfolioStockJpaRepository.saveAll(portfolioStocks.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockToPortfolioStockEntity)
                .toList()
        );
        List<PortfolioStockResponse> expected = portfolioStocks.stream().map(PortfolioStockResponse::from).toList();

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/portfolios/{portfolioId}", savedPortfolio.getId())
                .then().log().all()
                .extract();
        List<PortfolioStockResponse> result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("멤버 아이디로 해당 멤버의 모든 포트폴리오를 조회한다")
    void findAllPortfolios() {
        //given
        Member member1 = Member.builder().build();
        Member member2 = Member.builder().build();
        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);

        Stock apple = StockFixture.apple();
        Stock nvidia = StockFixture.nvidia();
        Stock savedApple = stockRepository.save(apple);
        Stock savedNvidia = stockRepository.save(nvidia);

        List<PortfolioStock> portfolioStocks1 = List.of(
                PortfolioStock.builder().stock(savedApple).portion(BigDecimal.valueOf(0.5)).build(),
                PortfolioStock.builder().stock(savedNvidia).portion(BigDecimal.valueOf(0.5)).build()
        );

        List<PortfolioStock> portfolioStocks2 = List.of(
                PortfolioStock.builder().stock(savedApple).portion(BigDecimal.valueOf(0.5)).build(),
                PortfolioStock.builder().stock(savedNvidia).portion(BigDecimal.valueOf(0.5)).build()
        );

        List<PortfolioStock> portfolioStocks3 = List.of(
                PortfolioStock.builder().stock(savedApple).portion(BigDecimal.valueOf(0.5)).build(),
                PortfolioStock.builder().stock(savedNvidia).portion(BigDecimal.valueOf(0.5)).build()
        );

        Portfolio portfolio1 = Portfolio.builder()
                .memberId(savedMember1.getId())
                .portfolioStocks(portfolioStocks1)
                .build();
        Portfolio portfolio2 = Portfolio.builder()
                .memberId(savedMember2.getId())
                .portfolioStocks(portfolioStocks2)
                .build();
        Portfolio portfolio3 = Portfolio.builder()
                .memberId(savedMember1.getId())
                .portfolioStocks(portfolioStocks3)
                .build();
        Portfolio savedPortfolio1 = portfolioRepository.save(portfolio1);
        Portfolio savedPortfolio2 = portfolioRepository.save(portfolio2);
        Portfolio savedPortfolio3 = portfolioRepository.save(portfolio3);

        List<PortfolioResponse> expected = List.of(savedPortfolio1, savedPortfolio3).stream()
                .map(PortfolioResponse::from)
                .toList();

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/portfolios")
                .then().log().all()
                .extract();
        List<PortfolioResponse> result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("특정 포트폴리오에 있는 포트폴리오 주식 정보들을 업데이트 한다")
    void updatePortfolio() {
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


        PortfolioStock portfolioStock = PortfolioStock.builder()
                .portfolioId(savedPortfolio1.getId())
                .stock(savedApple)
                .portion(BigDecimal.valueOf(1.0))
                .build();

        List<PortfolioStockResponse> expected = List.of(PortfolioStockResponse.from(portfolioStock));

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(updatePortfolioStockRequests)
                .when().put("/portfolios/{portfolioId}", savedPortfolio1.getId())
                .then().log().all()
                .extract();
        List<PortfolioStockResponse> result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
