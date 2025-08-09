package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주가 응답")
class StockPriceResponseTest {

    @Test
    @DisplayName("주가 응답을 주가로 변환한다")
    void toDomain() {
        // given
        StockPriceResponse stockPriceResponse = new StockPriceResponse(
                "2025-04-01T00:00:00",
                "223.19000244140625"
        );
        StockPrice expected = new StockPrice(
                null,
                1L,
                LocalDate.of(2025, 4, 1),
                new BigDecimal("223.19000244140625")
        );

        // when
        StockPrice result = stockPriceResponse.toStockPrice(1L);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
