package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.common;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
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
        URI expected = URI.create("https://api.nasdaq.com/api/quote/AAPL/dividends?assetclass=stocks");

        // when
        URI result = nasdaqUrlProvider.getDividendEndpoint("AAPL", "stocks");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("티커에 슬래시가 있을 때 배당 api endpoint 를 가져온다")
    void getDividendEndpointWithSlash() {
        // given
        URI expected = URI.create("https://api.nasdaq.com/api/quote/BRK%25sl%25B/dividends?assetclass=stocks");

        // when
        URI result = nasdaqUrlProvider.getDividendEndpoint("BRK/B", "stocks");

        // then
        assertThat(result).isEqualTo(expected);
    }
}
