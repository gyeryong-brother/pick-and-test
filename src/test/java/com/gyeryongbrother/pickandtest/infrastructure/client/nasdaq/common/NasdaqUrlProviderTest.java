package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Nasdaq api url 을 제공한다")
class NasdaqUrlProviderTest {

    private NasdaqUrlProvider nasdaqUrlProvider;

    @BeforeEach
    void setUp() {
        nasdaqUrlProvider = new NasdaqUrlProvider();
    }

    @Test
    @DisplayName("주식 티커 api endpoint 를 가져온다")
    void getStocksEndpoint() {
        // given
        String expected = "https://api.nasdaq.com/api/screener/stocks?tableonly=true&limit=5000&exchange=NASDAQ";

        // when
        String result = nasdaqUrlProvider.getStockSymbolEndpoint(NASDAQ);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("배당 api endpoint 를 가져온다")
    void getDividendEndpoint() {
        // given
        String expected = "https://api.nasdaq.com/api/quote/AAPL/dividends?assetclass=stocks";

        // when
        String result = nasdaqUrlProvider.getDividendEndpoint("AAPL", "stocks");

        // then
        assertThat(result).isEqualTo(expected);
    }
}
