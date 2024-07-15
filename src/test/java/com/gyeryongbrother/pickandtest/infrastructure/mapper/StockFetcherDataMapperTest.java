package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponseFixture.actualFetchStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponseFixture.firstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponseFixture.secondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponseFixture.thirdStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockFetcherDataMapperTest {

    private StockFetcherDataMapper stockFetcherDataMapper;

    @BeforeEach
    void setUp() {
        stockFetcherDataMapper = new StockFetcherDataMapper(new StockPriceFetcherDataMapper());
    }

    @Test
    void fetchStockResponseToStock() {
        // given
        FetchStockResponse fetchStockResponse = actualFetchStockResponse();
        List<StockPriceResponse> stockPriceResponses = List.of(
                firstStockPriceResponse(),
                secondStockPriceResponse(),
                thirdStockPriceResponse()
        );
        List<StockPrice> expectedStockPrices = List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98),
                stockPrice(9, 228.68),
                stockPrice(8, 227.82),
                stockPrice(5, 226.34),
                stockPrice(3, 221.55),
                stockPrice(2, 220.27)
        );
        Stock expected = Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .listingDate(null)
                .stockPrices(expectedStockPrices)
                .build();

        // when
        Stock result = stockFetcherDataMapper.fetchStockResponseToStock(
                fetchStockResponse,
                "AAPL",
                stockPriceResponses
        );

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    private StockPrice stockPrice(int day, double price) {
        return StockPrice.builder()
                .date(LocalDate.of(2024, 7, day))
                .price(BigDecimal.valueOf(price))
                .build();
    }
}
