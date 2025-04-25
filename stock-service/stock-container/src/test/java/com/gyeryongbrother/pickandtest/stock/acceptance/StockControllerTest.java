package com.gyeryongbrother.pickandtest.stock.acceptance;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.IncomeStatementFixture.incomeStatements;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockDetailFixture.firstStockDetail;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockDetailFixture.secondStockDetail;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneThousand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.stock.application.dto.CreateFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.FavoriteStockFixture;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockDetailRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
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
@DisplayName("주식 api 를 제공한다")
@Sql("/truncate.sql")
class StockControllerTest {

    @Autowired
    private IncomeStatementRepository incomeStatementRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockDetailRepository stockDetailRepository;

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
                stock("Advance Auto Parts, Inc.", "AAP"),
                stock("Microsoft Corporation", "MSFT"),
                stock("Apple Inc.", "AAPL"),
                stock("NVIDIA Corporation", "NVDA"),
                stock("AAP, Inc.", "AAPJ")
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
    @DisplayName("주식의 1년기준 기업실적을 가져온다")
    void findAnnualIncomeStatements() {
        //given
        Stock stock = stockRepository.save(stock(null));
        List<IncomeStatement> incomeStatements = incomeStatements(stock);
        incomeStatements.forEach(incomeStatementRepository::save);
        List<AnnualIncomeStatementResponse> expected = List.of(
                new AnnualIncomeStatementResponse(2020, 220L, 100L, 50L),
                new AnnualIncomeStatementResponse(2024, 420L, 200L, 100L)
        );

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/stocks/{stockId}/incomeStatements", stock.id())
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
        Long stockId = stockRepository.save(stock(null))
                .id();
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
        Stock firstStock = stockRepository.save(stock(null));
        Stock secondStock = stockRepository.save(stock(null));
        stockDetailRepository.save(firstStockDetail(firstStock.id()));
        stockDetailRepository.save(secondStockDetail(secondStock.id()));
        Stream.of(firstStock, secondStock)
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
