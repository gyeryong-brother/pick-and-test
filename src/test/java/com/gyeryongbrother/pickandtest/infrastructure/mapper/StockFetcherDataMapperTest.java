package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture.apple;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.appleStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleFirstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleSecondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleThirdStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void fetchStockResponseToStock() {
        // given
        StockResponse appleStockResponse = appleStockResponse();
        List<StockPriceResponse> appleStockPriceResponses = List.of(
                appleFirstStockPriceResponse(),
                appleSecondStockPriceResponse(),
                appleThirdStockPriceResponse()
        );
        DividendResponse appleDividendResponse = appleDividendResponse();
        Stock expected = apple();

        // when
        Stock result = stockFetcherDataMapper.stockResponseToStock(
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
