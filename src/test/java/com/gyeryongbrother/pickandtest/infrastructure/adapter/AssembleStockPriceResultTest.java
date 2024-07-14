package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponseFixture.firstFetchStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssembleStockPriceResultTest {

    private AssembleStockPriceResult assembleStockPriceResult;

    @BeforeEach
    void setUp() {
        assembleStockPriceResult = new AssembleStockPriceResult();
    }

    @Test
    void hasNextTrueWithEmpty() {
        // when
        boolean result = assembleStockPriceResult.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void hasNextTrueWithNext() {
        // given
        FetchStockPriceResponse fetchStockPriceResponse = new FetchStockPriceResponse(ContinuityCode.NEXT, null);
        assembleStockPriceResult.add(fetchStockPriceResponse);

        // when
        boolean result = assembleStockPriceResult.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void hasNextFalseWithEnd() {
        // given
        FetchStockPriceResponse nextResponse = new FetchStockPriceResponse(ContinuityCode.NEXT, null);
        assembleStockPriceResult.add(nextResponse);
        FetchStockPriceResponse endResponse = new FetchStockPriceResponse(ContinuityCode.END, null);
        assembleStockPriceResult.add(endResponse);

        // when
        boolean result = assembleStockPriceResult.hasNext();

        // then
        assertThat(result).isFalse();
    }

    @Test
    void getNextDateWithEmpty() {
        // given
        LocalDate expected = LocalDate.now();

        // when
        LocalDate result = assembleStockPriceResult.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getNextDate() {
        // given
        FetchStockPriceResponse fetchStockPriceResponse = firstFetchStockPriceResponse();
        assembleStockPriceResult.add(fetchStockPriceResponse);
        LocalDate expected = LocalDate.of(2024, 7, 9);

        // when
        LocalDate result = assembleStockPriceResult.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getStockPricesWithEmpty() {
        // given
        List<StockPrice> expected = List.of();

        // when
        List<StockPrice> result = assembleStockPriceResult.getStockPrices();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getStockPrices() {
        // given
        FetchStockPriceResponse fetchStockPriceResponse = firstFetchStockPriceResponse();
        assembleStockPriceResult.add(fetchStockPriceResponse);
        List<StockPrice> expected = List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98)
        );

        // when
        List<StockPrice> result = assembleStockPriceResult.getStockPrices();

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
