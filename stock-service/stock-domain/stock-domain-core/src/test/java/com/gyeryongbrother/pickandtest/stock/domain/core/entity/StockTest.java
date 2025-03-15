package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주식과 관련된 기능을 제공한다")
class StockTest {

    @Test
    @DisplayName("주가를 받아 시가총액을 계산한다")
    void calculateMarketCapitalization() {
        // given
        StockDetail stockDetail = StockDetail.builder()
                .lastStockPrice(BigDecimal.valueOf(20.5))
                .build();
        Stock stock = Stock.builder()
                .outstandingShares(1000L)
                .stockDetail(stockDetail)
                .build();

        // when
        Long result = stock.calculateMarketCapitalization();

        // then
        assertThat(result).isEqualTo(20_500L);
    }
}
