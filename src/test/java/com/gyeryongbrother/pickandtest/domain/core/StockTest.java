package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.MarketCapitalizationFixture.marketCapitalization;
import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrice;
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
        Stock stock = stock(1L);
        StockPrice stockPrice = stockPrice(1L, januaryFirst(), BigDecimal.valueOf(20.5));
        MarketCapitalization expected = marketCapitalization(20_500L);

        // when
        MarketCapitalization result = stock.calculateMarketCapitalization(stockPrice);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
