package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.ContinuityCode.END;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.ContinuityCode.NEXT;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.stockPriceBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("100개씩 나눠져서 오는 주가 api 응답을 통합한다")
class StockPriceAssemblerTest {

    @Mock
    private DateProvider dateProvider;

    private StockPriceAssembler stockPriceAssembler;

    @BeforeEach
    void setUp() {
        stockPriceAssembler = new StockPriceAssembler(
                LocalDate.of(2025, 3, 1),
                dateProvider
        );
    }

    @Test
    @DisplayName("조회 시작일이 3월 1일인데 오늘 날짜가 2월 1일이면 조회가 필요하지 않다")
    void hasNextWhenDateIsBeforeToday() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 2, 1));

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("조회 시작일이 3월 1일인데 오늘 날짜가 3월 1일이면 조회가 필요하다")
    void hasNextWhenDateIsToday() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 3, 1));

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("조회 시작일이 3월 1일인데 오늘 날짜가 4월 1일이면 조회가 필요하다")
    void hasNextWhenDateIsAfterToday() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 4, 1));

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("초기 상태에서 오늘 날짜가 조회 시작일보다 과거가 아니라면 조회가 필요하다")
    void hasNextTrueWithEmpty() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 3, 1));

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("조회된 날짜 중 가장 과거의 날짜가 조회 시작일과 같을 때 조회가 필요하지 않다")
    void hasNextWhenLastDateIsDate() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 3, 1));
        stockPriceAssembler.add(new StockPriceResponse(NEXT, stockPriceBody("20250301")));

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("조회된 날짜 중 가장 과거의 날짜가 조회 시작일보다 과거이면 조회가 필요하지 않다")
    void hasNextWhenLastDateIsBeforeDate() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 3, 1));
        stockPriceAssembler.add(new StockPriceResponse(NEXT, stockPriceBody("20250201")));

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("조회된 날짜 중 가장 과거의 날짜가 조회 시작일보다 미래이고 다음 응답이 존재하면 조회가 필요하다")
    void hasNextWhenLastDateIsAfterDateWithNext() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 3, 1));
        stockPriceAssembler.add(new StockPriceResponse(NEXT, stockPriceBody("20250401")));

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("조회된 날짜 중 가장 과거의 날짜가 조회 시작일보다 미래이고 다음 응답이 없으면 조회가 필요하지 않다")
    void hasNextTrueWithNext() {
        // given
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 4, 1));
        StockPriceResponse stockPriceResponse = new StockPriceResponse(END, stockPriceBody("20250401"));
        stockPriceAssembler.add(stockPriceResponse);

        // when
        boolean result = stockPriceAssembler.hasNext();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("초기 상태에서 다음 날짜는 오늘이다")
    void getNextDateWithEmpty() {
        // given
        LocalDate expected = LocalDate.of(2025, 4, 1);
        given(dateProvider.now())
                .willReturn(LocalDate.of(2025, 4, 1));

        // when
        LocalDate result = stockPriceAssembler.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 날짜는 가장 과거 날짜 하루 전이다")
    void getNextDate() {
        // given
        StockPriceResponse stockPriceResponse = new StockPriceResponse(END, stockPriceBody("20250401"));
        stockPriceAssembler.add(stockPriceResponse);
        LocalDate expected = LocalDate.of(2025, 3, 31);

        // when
        LocalDate result = stockPriceAssembler.getNextDate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("주가를 조회할 때 조회 시작일 과거 주가는 제외한다")
    void stockPrices() {
        // given
        StockPriceResponse stockPriceResponse = new StockPriceResponse(END,
                stockPriceBody("20250302", "20250301", "20250228", "20250227"));
        List<StockPrice> expected = List.of(
                new StockPrice(null, 1L, LocalDate.of(2025, 3, 2), new BigDecimal("230.5400")),
                new StockPrice(null, 1L, LocalDate.of(2025, 3, 1), new BigDecimal("230.5400"))
        );

        // when
        stockPriceAssembler.add(stockPriceResponse);
        List<StockPrice> result = stockPriceAssembler.stockPrices(1L);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
