package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.MarketCapitalizationFixture.marketCapitalization;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.MarketCapitalization;
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
