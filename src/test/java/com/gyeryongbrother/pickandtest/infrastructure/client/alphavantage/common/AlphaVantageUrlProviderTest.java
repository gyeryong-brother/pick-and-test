package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Alpha Vantage api url 을 제공한다")
class AlphaVantageUrlProviderTest {

    private AlphaVantageUrlProvider alphaVantageUrlProvider;

    @BeforeEach
    void setUp() {
        alphaVantageUrlProvider = new AlphaVantageUrlProvider(new AlphaVantageClientCredential("apiKey"));
    }

    @Test
    @DisplayName("배당 api endpoint 를 가져온다")
    void getDividendEndpoint() {
        // given
        String expected = "https://www.alphavantage.co/query?function=DIVIDENDS&symbol=AAPL&apikey=apiKey";

        // when
        String result = alphaVantageUrlProvider.getDividendEndpoint("AAPL");

        // then
        assertThat(result).isEqualTo(expected);
    }
}
