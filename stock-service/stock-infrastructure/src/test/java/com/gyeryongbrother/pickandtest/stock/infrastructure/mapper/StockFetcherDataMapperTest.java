package com.gyeryongbrother.pickandtest.stock.infrastructure.mapper;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.StockDetailFixture.apple;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.appleStockResponse;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleFirstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleSecondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleThirdStockPriceResponse;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto.DividendResponse;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주식 응답과 주식을 매핑한다")
class StockFetcherDataMapperTest {

    private StockFetcherDataMapper stockFetcherDataMapper;

    @BeforeEach
    void setUp() {
        stockFetcherDataMapper = new StockFetcherDataMapper(
                new StockPriceFetcherDataMapper(),
                new DividendFetcherDataMapper()
        );
    }

    @Test
    @DisplayName("주식 응답을 주식으로 변환한다")
    void fetchStockResponseToStock() {
        // given
        StockResponse appleStockResponse = appleStockResponse();
        List<StockPriceResponse> appleStockPriceResponses = List.of(
                appleFirstStockPriceResponse(),
                appleSecondStockPriceResponse(),
                appleThirdStockPriceResponse()
        );
        DividendResponse appleDividendResponse = appleDividendResponse();
        StockDetail expected = apple();

        // when
        StockDetail result = stockFetcherDataMapper.stockResponseToStockDetail(
                appleStockResponse,
                "AAPL",
                NASDAQ,
                appleStockPriceResponses,
                appleDividendResponse
        );

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
