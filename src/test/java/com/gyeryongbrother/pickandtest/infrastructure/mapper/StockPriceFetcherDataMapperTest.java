package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.firstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.secondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.thirdStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockPriceFetcherDataMapperTest {

    private StockPriceFetcherDataMapper stockPriceFetcherDataMapper;

    @BeforeEach
    void setUp() {
        stockPriceFetcherDataMapper = new StockPriceFetcherDataMapper();
    }

    @Test
    void stockPriceResponsesToStockPricesByThree() {
        // given
        List<StockPriceResponse> stockPriceResponses = List.of(
                firstStockPriceResponse(),
                secondStockPriceResponse(),
                thirdStockPriceResponse()
        );
        List<StockPrice> expected = List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98),
                stockPrice(9, 228.68),
                stockPrice(8, 227.82),
                stockPrice(5, 226.34),
                stockPrice(3, 221.55),
                stockPrice(2, 220.27)
        );

        // when
        List<StockPrice> result = stockPriceFetcherDataMapper.stockPriceResponsesToStockPrices(stockPriceResponses);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    void stockPriceResponsesToStockPrices() {
        // given
        List<StockPriceResponse> stockPriceResponses = List.of(firstStockPriceResponse());
        List<StockPrice> expected = List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98)
        );

        // when
        List<StockPrice> result = stockPriceFetcherDataMapper.stockPriceResponsesToStockPrices(stockPriceResponses);

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
