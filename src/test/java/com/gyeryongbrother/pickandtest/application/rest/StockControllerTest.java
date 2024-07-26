package com.gyeryongbrother.pickandtest.application.rest;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntities;
import static com.gyeryongbrother.pickandtest.domain.service.dto.MarketCapitalizationResponseFixture.marketCapitalizationResponses;
import static com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponseFixture.stockPriceResponses;
import static com.gyeryongbrother.pickandtest.domain.service.dto.StockResponseFixture.stockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.application.dto.CreateFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockDetailFixture;
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
@DisplayName("주식 api 를 제공한다")
@Sql("/truncate.sql")
class StockControllerTest {

    @Autowired
    private StockJpaRepository stockJpaRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockPriceJpaRepository stockPriceJpaRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("키워드로 주식을 검색한다")
    void searchStocks() {
        // given
        stockJpaRepository.saveAll(List.of(
                stockEntity("Advance Auto Parts, Inc.", "AAP"),
                stockEntity("Microsoft Corporation", "MSFT"),
                stockEntity("Apple Inc.", "AAPL"),
                stockEntity("NVIDIA Corporation", "NVDA"),
                stockEntity("AAP, Inc.", "AAPJ")
        ));
        List<StockResponse> expected = List.of(
                stockResponse("Advance Auto Parts, Inc.", "AAP"),
                stockResponse("Apple Inc.", "AAPL"),
                stockResponse("AAP, Inc.", "AAPJ")
        );

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .queryParam("keyword", "AAP")
                .when().get("/stocks")
                .then().log().all()
                .extract();
        List<StockResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주식 아이디로 시가총액을 조회한다")
    void findAllMarketCapitalizations() {
        // given
        StockEntity stockEntity = stockEntity();
        stockPriceEntities(null).forEach(stockEntity::addStockPrice);
        stockJpaRepository.save(stockEntity);
        List<MarketCapitalizationResponse> expected = marketCapitalizationResponses();

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/market-capitalizations", stockEntity.getId())
                .then().log().all()
                .extract();
        List<MarketCapitalizationResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주식의 주가들을 가져온다")
    void findAllStockPrices() {
        // given
        StockEntity stockEntity = stockJpaRepository.save(stockEntity());
        stockPriceJpaRepository.saveAll(stockPriceEntities(stockEntity));
        List<StockPriceResponse> expected = stockPriceResponses();

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/prices", stockEntity.getId())
                .then().log().all()
                .extract();
        List<StockPriceResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주식의 연배당기록을 가져온다")
    void findAnnualDividends() {
        // given
        StockDetail stockDetail = StockDetailFixture.appleWithDividendsOfVariousYear();
        StockDetail saved = stockRepository.save(stockDetail);
        List<AnnualDividendResponse> expected = List.of(
                new AnnualDividendResponse(2020, BigDecimal.valueOf(0.45)),
                new AnnualDividendResponse(2021, BigDecimal.valueOf(0.32))
        );

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/dividends", saved.getStock().getId())
                .then().log().all()
                .extract();
        List<AnnualDividendResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("관심 주식을 저장한다")
    void createFavoriteStock() {
        // given
        CreateFavoriteStockRequest createFavoriteStockRequest = new CreateFavoriteStockRequest(1L);
        Long stockId = stockJpaRepository.save(stockEntity())
                .getId();
        CreateFavoriteStockResponse expected = new CreateFavoriteStockResponse(null, 1L, stockId);

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(createFavoriteStockRequest)
                .when().post("/stocks/{stockId}/favorite", stockId)
                .then().log().all()
                .extract();
        CreateFavoriteStockResponse result = response.as(CreateFavoriteStockResponse.class);

        // then
        assertAll(
                () -> assertThat(result.id()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(expected)
        );
    }
}
