package com.gyeryongbrother.pickandtest.application.rest;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntities;
import static com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponseFixture.stockPriceResponses;
import static com.gyeryongbrother.pickandtest.domain.service.dto.StockResponseFixture.stockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
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
}
