package com.gyeryongbrother.pickandtest.application.rest;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("배당금 api를 제공한다")
class DividendControllerTest {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private DividendRepository dividendRepository;
    @Autowired
    private EntityManager entityManager;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    @Test
    @DisplayName("이름으로 배당기록을 가져온다")
    void findAnnualDividendsById() {
        //given
        String name = "Apple";
        String symbol = "AAPL";
        Stock stock = Stock.builder()
                .name(name)
                .symbol(symbol)
                .build();
        Stock savedStock = stockRepository.save(stock);

        LocalDate date1 = LocalDate.of(2020, 3, 1);
        LocalDate date2 = LocalDate.of(2020, 6, 1);
        LocalDate date3 = LocalDate.of(2021, 4, 1);
        BigDecimal amount1 = BigDecimal.valueOf(0.22);
        BigDecimal amount2 = BigDecimal.valueOf(0.23);
        BigDecimal amount3 = BigDecimal.valueOf(0.32);
        Dividend dividend1 = dividend(savedStock, date1, amount1);
        Dividend dividend2 = dividend(savedStock, date2, amount2);
        Dividend dividend3 = dividend(savedStock, date3, amount3);
        List<Dividend> dividends = new ArrayList<Dividend>();
        dividendRepository.save(dividend1);
        dividendRepository.save(dividend2);
        dividendRepository.save(dividend3);
        List<AnnualDividendResponse> expected = new ArrayList<AnnualDividendResponse>();
        expected.add(new AnnualDividendResponse(2020, BigDecimal.valueOf(0.45)));
        expected.add(new AnnualDividendResponse(2021, BigDecimal.valueOf(0.32)));
        entityManager.clear();

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/{stockId}/dividend", savedStock.getId())
                .then().log().all()
                .extract();
        List<AnnualDividendResponse> result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    private Dividend dividend(Stock stock, LocalDate date, BigDecimal amount) {
        return Dividend.builder()
                .stockId(stock.getId())
                .date(date)
                .amount(amount)
                .build();
    }
}
