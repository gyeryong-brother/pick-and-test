package com.gyeryongbrother.pickandtest.stock.acceptance;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.twoDividends;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.IncomeStatementFixture.incomeStatements;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockDetailFixture.appleWithDividendsOfVariousYear;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.stockPrices;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneThousand;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januarySecond;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.stock.application.dto.CreateFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.FavoriteStockFixture;
import com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
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
    private IncomeStatementRepository incomeStatementRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private FavoriteStockRepository favoriteStockRepository;

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
        List<Stock> stocks = List.of(
                StockFixture.stock("Advance Auto Parts, Inc.", "AAP"),
                StockFixture.stock("Microsoft Corporation", "MSFT"),
                StockFixture.stock("Apple Inc.", "AAPL"),
                StockFixture.stock("NVIDIA Corporation", "NVDA"),
                StockFixture.stock("AAP, Inc.", "AAPJ")
        );
        stocks.forEach(stockRepository::save);
        List<StockResponse> expected = List.of(
                new StockResponse(null, "Advance Auto Parts, Inc.", "AAP"),
                new StockResponse(null, "Apple Inc.", "AAPL"),
                new StockResponse(null, "AAP, Inc.", "AAPJ")
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
        StockDetail stockDetail = stockRepository.save(stockDetail(null, stockPrices(null), List.of()));
        List<MarketCapitalizationResponse> expected = List.of(
                new MarketCapitalizationResponse(januaryFirst(), 100_000L),
                new MarketCapitalizationResponse(januarySecond(), 200_000L)
        );

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/market-capitalizations", stockDetail.getStock().getId())
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
        StockDetail stockDetail = stockRepository.save(
                stockDetail(null, stockPrices(null), List.of()));
        List<StockPriceResponse> expected = List.of(
                new StockPriceResponse(januaryFirst(), oneHundred()),
                new StockPriceResponse(januarySecond(), twoHundred())
        );

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/prices", stockDetail.getStock().getId())
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
        StockDetail stockDetail = appleWithDividendsOfVariousYear();
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
        Stock stock = stockRepository.save(StockFixture.stock(null));
        List<IncomeStatement> incomeStatements = incomeStatements(stock);
        incomeStatements.forEach(incomeStatementRepository::save);
        List<AnnualIncomeStatementResponse> expected = List.of(
                new AnnualIncomeStatementResponse(2020, 220L, 100L, 50L),
                new AnnualIncomeStatementResponse(2024, 420L, 200L, 100L)
        );

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/incomeStatements", stock.getId())
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
        Long stockId = stockRepository.save(StockFixture.stock(null))
                .getId();
        CreateFavoriteStockRequest createFavoriteStockRequest = new CreateFavoriteStockRequest(stockId, 1L);
        CreateFavoriteStockResponse expected = new CreateFavoriteStockResponse(null, 1L, stockId);

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(createFavoriteStockRequest)
                .when().post("/favorite-stocks")
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
                stockRepository.save(
                        stockDetail(null, stockPrices(), dividends())),
                stockRepository.save(stockDetail(null, List.of(stockPrice()), twoDividends()))
        );
        stockDetails.stream()
                .map(FavoriteStockFixture::favoriteStock)
                .forEach(favoriteStockRepository::save);
        List<FavoriteStockResponse> expected = List.of(
                favoriteStockResponse(oneThousand(), BigDecimal.valueOf(58.49),
                        BigDecimal.valueOf(0.2)),
                favoriteStockResponse(oneHundred(), BigDecimal.ZERO, BigDecimal.valueOf(0.5))
        );

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/favorite-stocks")
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

    private FavoriteStockResponse favoriteStockResponse(
            BigDecimal price,
            BigDecimal compoundAnnualGrowthRate,
            BigDecimal dividendYield
    ) {
        return new FavoriteStockResponse(
                null,
                null,
                "Apple Inc.",
                "AAPL",
                price,
                compoundAnnualGrowthRate,
                dividendYield
        );
    }
}
