package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.appleStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

@DisplayName("주가 응답과 주가를 매핑한다")
class StockPriceFetcherDataMapperTest {

    private StockPriceFetcherDataMapper stockPriceFetcherDataMapper;

    @BeforeEach
    void setUp() {
        stockPriceFetcherDataMapper = new StockPriceFetcherDataMapper();
    }

    @Test
    @DisplayName("주가 응답들을 주가들로 변환한다")
    void stockPriceResponsesToStockPricesByThree() {
        // given
        List<StockPriceResponse> appleStockPriceResponses = List.of(
                appleFirstStockPriceResponse(),
                appleSecondStockPriceResponse(),
                appleThirdStockPriceResponse()
        );
        List<StockPrice> expected = appleStockPrices();

        // when
        List<StockPrice> result =
                stockPriceFetcherDataMapper.stockPriceResponsesToStockPrices(appleStockPriceResponses);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주가 응답을 주가들로 변환한다")
    void stockPriceResponsesToStockPrices() {
        // given
        List<StockPriceResponse> stockPriceResponses = List.of(appleFirstStockPriceResponse());
        List<StockPrice> expected = List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98)
        );

        // when
        List<StockPrice> result = stockPriceFetcherDataMapper.stockPriceResponsesToStockPrices(stockPriceResponses);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
