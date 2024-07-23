package com.gyeryongbrother.pickandtest.application.rest;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.domain.service.dto.StockResponseFixture.stockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("주식 api 를 제공한다")
class StockControllerTest {

    @Autowired
    private StockJpaRepository stockJpaRepository;

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
}
