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

class StockPriceAssemblerTest {

    private StockPriceAssembler stockPriceAssembler;

    @BeforeEach
    void setUp() {
        stockPriceAssembler = new StockPriceAssembler();
    }

    @Test
    void hasNextTrueWithEmpty() {
        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void hasNextTrueWithNext() {
        // given
        FetchStockPriceResponse fetchStockPriceResponse = new FetchStockPriceResponse(ContinuityCode.NEXT, null);
        stockPriceAssembler.add(fetchStockPriceResponse);

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void hasNextFalseWithEnd() {
        // given
        FetchStockPriceResponse nextResponse = new FetchStockPriceResponse(ContinuityCode.NEXT, null);
        stockPriceAssembler.add(nextResponse);
        FetchStockPriceResponse endResponse = new FetchStockPriceResponse(ContinuityCode.END, null);
        stockPriceAssembler.add(endResponse);

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isFalse();
    }

    @Test
    void getNextDateWithEmpty() {
        // given
        LocalDate expected = LocalDate.now();

        // when
        LocalDate result = stockPriceAssembler.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getNextDate() {
        // given
        FetchStockPriceResponse fetchStockPriceResponse = firstFetchStockPriceResponse();
        stockPriceAssembler.add(fetchStockPriceResponse);
        LocalDate expected = LocalDate.of(2024, 7, 9);

        // when
        LocalDate result = stockPriceAssembler.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getStockPricesWithEmpty() {
        // given
        List<StockPrice> expected = List.of();

        // when
        List<StockPrice> result = stockPriceAssembler.getStockPrices();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getStockPrices() {
        // given
        FetchStockPriceResponse fetchStockPriceResponse = firstFetchStockPriceResponse();
        stockPriceAssembler.add(fetchStockPriceResponse);
        List<StockPrice> expected = List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98)
        );

        // when
        List<StockPrice> result = stockPriceAssembler.getStockPrices();

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
