package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPriceFixture.stockPrices;
import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockWithPricesFixture.stockWithPrices;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.MarketCapitalizationFixture.marketCapitalizations;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.MarketCapitalization;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주가들과 관련된 기능을 제공한다")
class StockWithPricesTest {

    @Test
    @DisplayName("시가총액을 가져온다")
    void getMarketCapitalizations() {
        // given
        StockWithPrices stockWithPrices = stockWithPrices(1L, stockPrices(1L));
        List<MarketCapitalization> expected = marketCapitalizations();

        // when
        List<MarketCapitalization> result = stockWithPrices.getMarketCapitalizations();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
