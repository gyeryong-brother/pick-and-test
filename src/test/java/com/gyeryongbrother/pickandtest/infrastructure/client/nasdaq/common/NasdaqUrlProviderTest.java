package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NasdaqUrlProviderTest {

    private NasdaqUrlProvider nasdaqUrlProvider;

    @BeforeEach
    void setUp() {
        nasdaqUrlProvider = new NasdaqUrlProvider();
    }

    @Test
    void getStocksEndpoint() {
        // given
        String expected = "https://api.nasdaq.com/api/screener/stocks?tableonly=true&limit=5000&exchange=NASDAQ";

        // when
        String result = nasdaqUrlProvider.getStockSymbolEndpoint(NASDAQ);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
