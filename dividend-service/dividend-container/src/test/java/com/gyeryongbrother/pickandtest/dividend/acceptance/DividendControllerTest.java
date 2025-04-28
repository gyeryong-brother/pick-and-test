package com.gyeryongbrother.pickandtest.dividend.acceptance;

import static com.gyeryongbrother.pickandtest.dividend.domain.fixture.valueobject.DividendsFixture.appleDividendsAtVariousYear;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject.Dividends;
import com.gyeryongbrother.pickandtest.dividend.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendRepository;
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
@DisplayName("배당 api 를 제공한다")
@Sql("/truncate.sql")
public class DividendControllerTest {

    @Autowired
    private DividendRepository dividendRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("주식의 연배당기록을 가져온다")
    void findAnnualDividends() {
        // given
        Dividends dividends = appleDividendsAtVariousYear(1L);
        dividends.values().forEach(dividendRepository::save);
        List<AnnualDividendResponse> expected = List.of(
                new AnnualDividendResponse(2020, BigDecimal.valueOf(0.45)),
                new AnnualDividendResponse(2021, BigDecimal.valueOf(0.32))
        );

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/dividend-service/dividends?stockId={stockId}", 1)
                .then().log().all()
                .extract();
        List<AnnualDividendResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
