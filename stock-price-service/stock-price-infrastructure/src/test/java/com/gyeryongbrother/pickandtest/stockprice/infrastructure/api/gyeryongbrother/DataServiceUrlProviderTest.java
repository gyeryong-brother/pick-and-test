package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("데이터 서비스 api url 을 제공한다")
class DataServiceUrlProviderTest {

    @Test
    @DisplayName("주가 api endpoint 를 가져온다")
    void getStockPriceEndpoint() {
        // given
        DataServiceUrlProvider dataServiceUrlProvider = new DataServiceUrlProvider("http://domain");
        String expected = "http://domain/stock-prices?tickers=symbol&start=2025-04-18";

        // when
        String result = dataServiceUrlProvider.getStockPriceEndpoint(
                List.of("symbol"),
                LocalDate.of(2025, 4, 18)
        );

        // then
        assertThat(result).isEqualTo(expected);
    }
}
