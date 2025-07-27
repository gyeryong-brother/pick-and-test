package com.gyeryongbrother.pickandtest.stockprice.acceptance;

import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.entity.StockPriceFixture.stockPrices;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.LocalDateFixture.januarySecond;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
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
class StockPriceControllerTest {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("주식의 주가들을 가져온다")
    void findAllStockPrices() {
        // given
        stockPrices(1L).forEach(stockPriceRepository::save);
        List<StockPriceResponse> expected = List.of(
                new StockPriceResponse(januaryFirst(), oneHundred()),
                new StockPriceResponse(januarySecond(), twoHundred())
        );

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stock-price-service/stock-prices?stockId={stockId}", 1)
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
