package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.common;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.common.StockServiceUrlProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주식 서비스 api url 을 제공한다")
class StockServiceUrlProviderTest {

    private StockServiceUrlProvider stockServiceUrlProvider;

    @BeforeEach
    void setUp() {
        stockServiceUrlProvider = new StockServiceUrlProvider();
    }

    @Test
    @DisplayName("주식 api endpoint 를 가져온다")
    void getStockEndpoint() {
        // given
        String expected = "http://localhost:8080/stocks/1";

        // when
        String result = stockServiceUrlProvider.getStockEndpoint(1L);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
