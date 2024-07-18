package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleFirstStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
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
        StockPriceResponse stockPriceResponse = new StockPriceResponse(ContinuityCode.NEXT, null);
        stockPriceAssembler.add(stockPriceResponse);

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void hasNextFalseWithEnd() {
        // given
        StockPriceResponse nextResponse = new StockPriceResponse(ContinuityCode.NEXT, null);
        stockPriceAssembler.add(nextResponse);
        StockPriceResponse endResponse = new StockPriceResponse(ContinuityCode.END, null);
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
        StockPriceResponse stockPriceResponse = appleFirstStockPriceResponse();
        stockPriceAssembler.add(stockPriceResponse);
        LocalDate expected = LocalDate.of(2024, 7, 9);

        // when
        LocalDate result = stockPriceAssembler.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
