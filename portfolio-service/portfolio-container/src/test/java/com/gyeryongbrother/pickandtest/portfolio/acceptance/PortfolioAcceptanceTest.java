package com.gyeryongbrother.pickandtest.portfolio.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.gyeryongbrother.pickandtest.portfolio.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioRequest;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioStockRequest;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioStockResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
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
class PortfolioAcceptanceTest {

    @Autowired
    private PortfolioRepository portfolioRepository;

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
        portfolioStocks.forEach(portfolioStockRepository::save);
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
        Portfolio portfolio1 = Portfolio.builder()
                .memberId(1L)
                .build();
        Portfolio portfolio2 = Portfolio.builder()
                .memberId(2L)
                .build();
        Portfolio portfolio3 = Portfolio.builder()
                .memberId(1L)
                .build();
        Portfolio savedPortfolio1 = portfolioRepository.save(portfolio1);
        portfolioRepository.save(portfolio2);
        Portfolio savedPortfolio3 = portfolioRepository.save(portfolio3);

        List<PortfolioResponse> expected = Stream.of(savedPortfolio1, savedPortfolio3)
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
    @DisplayName("포트폴리오를 업데이트 한다")
    void updatePortfolio() {
        //given
        PortfolioStock portfolioStock1 = new PortfolioStock(null, null, 1L, BigDecimal.valueOf(0.5));
        PortfolioStock portfolioStock2 = new PortfolioStock(null, null, 2L, BigDecimal.valueOf(0.5));

        Portfolio portfolio1 = new Portfolio(null, 1L, List.of(portfolioStock1, portfolioStock2));
        Portfolio savedPortfolio = portfolioRepository.save(portfolio1);

        UpdatePortfolioStockRequest updatePortfolioStockRequest1 = new UpdatePortfolioStockRequest(3L,
                BigDecimal.valueOf(0.5));
        UpdatePortfolioStockRequest updatePortfolioStockRequest2 = new UpdatePortfolioStockRequest(4L,
                BigDecimal.valueOf(0.5));
        UpdatePortfolioRequest updatePortfolioRequest = new UpdatePortfolioRequest(
                List.of(updatePortfolioStockRequest1, updatePortfolioStockRequest2));

        UpdatePortfolioStockResponse updatePortfolioStockResponse1 = new UpdatePortfolioStockResponse(3L,
                BigDecimal.valueOf(0.5));
        UpdatePortfolioStockResponse updatePortfolioStockResponse2 = new UpdatePortfolioStockResponse(4L,
                BigDecimal.valueOf(0.5));
        UpdatePortfolioResponse expected = new UpdatePortfolioResponse(
                List.of(updatePortfolioStockResponse1, updatePortfolioStockResponse2),
                1L
        );

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(updatePortfolioRequest)
                .when().put("/portfolios/{portfolioId}", savedPortfolio.getId())
                .then().log().all()
                .extract();

        UpdatePortfolioResponse result = response.as(UpdatePortfolioResponse.class);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("포트폴리오를 업데이트 한다")
    void updatePortfolioByNonExistId() {
        //given
        UpdatePortfolioRequest updatePortfolioRequest = new UpdatePortfolioRequest(List.of());
        ErrorResponse expected = new ErrorResponse("포트폴리오가 존재하지 않습니다");

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(updatePortfolioRequest)
                .when().put("/portfolios/{portfolioId}", -1)
                .then().log().all()
                .extract();

        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }
}
