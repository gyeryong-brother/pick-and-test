package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.MarketCapitalizationFixture.marketCapitalization;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrice;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주가와 관련된 기능을 제공한다")
class StockPriceTest {

    @Test
    @DisplayName("유통주식수를 받아 시가총액을 계산한다")
    void calculateMarketCapitalization() {
        // given
        StockPrice stockPrice = stockPrice(1L, januaryFirst(), BigDecimal.valueOf(30.2));
        MarketCapitalization expected = marketCapitalization(60L);

        // when
        MarketCapitalization result = stockPrice.calculateMarketCapitalization(2L);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
