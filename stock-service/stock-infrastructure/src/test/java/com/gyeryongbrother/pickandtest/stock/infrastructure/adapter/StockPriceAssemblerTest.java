package com.gyeryongbrother.pickandtest.stock.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.ContinuityCode.END;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.ContinuityCode.NEXT;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleFirstStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("100개씩 나눠져서 오는 주가 api 응답을 통합한다")
class StockPriceAssemblerTest {

    private StockPriceAssembler stockPriceAssembler;

    @BeforeEach
    void setUp() {
        stockPriceAssembler = new StockPriceAssembler();
    }

    @Test
    @DisplayName("초기 상태에서 다음 응답은 존재한다")
    void hasNextTrueWithEmpty() {
        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("최근 응답의 지속 코드가 NEXT 면 다음 응답은 존재한다")
    void hasNextTrueWithNext() {
        // given
        StockPriceResponse stockPriceResponse = new StockPriceResponse(NEXT, null);
        stockPriceAssembler.add(stockPriceResponse);

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("최근 응답의 지속 코드가 END 면 다음 응답은 존재하지 않는다")
    void hasNextFalseWithEnd() {
        // given
        StockPriceResponse nextResponse = new StockPriceResponse(NEXT, null);
        stockPriceAssembler.add(nextResponse);
        StockPriceResponse endResponse = new StockPriceResponse(END, null);
        stockPriceAssembler.add(endResponse);

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("초기 상태에서 다음 날짜는 오늘이다")
    void getNextDateWithEmpty() {
        // given
        LocalDate expected = LocalDate.now();

        // when
        LocalDate result = stockPriceAssembler.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 날짜는 가장 과거 날짜 하루 전이다")
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
