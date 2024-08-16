package com.gyeryongbrother.pickandtest.application.rest;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntityFixture.incomeStatementEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntities;
import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.twoDividends;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.twentyTwenty;
import static com.gyeryongbrother.pickandtest.domain.core.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrices;
import static com.gyeryongbrother.pickandtest.domain.service.dto.FavoriteStockResponseFixture.favoriteStockResponses;
import static com.gyeryongbrother.pickandtest.domain.service.dto.MarketCapitalizationResponseFixture.marketCapitalizationResponses;
import static com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponseFixture.stockPriceResponses;
import static com.gyeryongbrother.pickandtest.domain.service.dto.StockResponseFixture.stockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.application.dto.CreateFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.IncomeStatementJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.FavoriteStockFixture;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockRepository;
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
    IncomeStatementJpaRepository incomeStatementJpaRepository;
    @Autowired
    private StockJpaRepository stockJpaRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockPriceJpaRepository stockPriceJpaRepository;
    @Autowired
    private FavoriteStockRepository favoriteStockRepository;
    @Autowired
    private StockDataAccessMapper stockDataAccessMapper;
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
    @DisplayName("주식의 1년기준 기업실적을 가져온다")
    void findAnnualIncomeStatements() {
        //given
        StockEntity appleEntity = stockJpaRepository.save(StockEntityFixture.stockEntity());
        Stock apple = stockDataAccessMapper.stockEntityToStock(appleEntity);
        List<IncomeStatementEntity> appleIncomeStatementEntities = List.of(
                incomeStatementEntity(appleEntity, 200L, 100L, 50L,
                        januaryFirst()),
                incomeStatementEntity(appleEntity, 220L, 100L, 50L,
                        januarySecond()),
                incomeStatementEntity(appleEntity, 220L, 100L, 50L,
                        twentyTwenty())
        );
        incomeStatementJpaRepository.saveAll(appleIncomeStatementEntities);
        List<AnnualIncomeStatementResponse> expected = List.of(
                new AnnualIncomeStatementResponse(2020, 220L, 100L, 50L),
                new AnnualIncomeStatementResponse(2024, 420L, 200L, 100L)
        );

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/incomeStatements", apple.getId())
                .then().log().all()
                .extract();
        List<AnnualIncomeStatementResponse> result = response.as(new TypeRef<>() {
        });

        //then
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

    @Test
    @DisplayName("관심 주식들을 조회한다")
    void findAllFavoriteStocks() {
        // given
        List<StockDetail> stockDetails = List.of(
                stockRepository.save(stockDetail(null, stockPrices(), dividends())),
                stockRepository.save(stockDetail(null, List.of(stockPrice()), twoDividends()))
        );
        stockDetails.stream()
                .map(FavoriteStockFixture::favoriteStock)
                .forEach(favoriteStockRepository::save);
        List<FavoriteStockResponse> expected = favoriteStockResponses();

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/favorite")
                .then().log().all()
                .extract();
        List<FavoriteStockResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
