package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.MarketCapitalizationFixture.marketCapitalizations;
import static com.gyeryongbrother.pickandtest.domain.core.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrices;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주식 상세와 관련된 기능을 제공한다")
class StockDetailTest {

    @Test
    @DisplayName("시가총액을 가져온다")
    void getMarketCapitalizations() {
        // given
        StockDetail stockDetail = stockDetail(1L, stockPrices(1L), List.of());
        List<MarketCapitalization> expected = marketCapitalizations();

        // when
        List<MarketCapitalization> result = stockDetail.getMarketCapitalizations();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
