package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlphaVantageUrlProviderTest {

    private AlphaVantageUrlProvider alphaVantageUrlProvider;

    @BeforeEach
    void setUp() {
        alphaVantageUrlProvider = new AlphaVantageUrlProvider(new AlphaVantageClientCredential("apiKey"));
    }

    @Test
    void getDividendEndpoint() {
        // given
        String expected = "https://www.alphavantage.co/query?function=DIVIDENDS&symbol=AAPL&apikey=apiKey";

        // when
        String result = alphaVantageUrlProvider.getDividendEndpoint("AAPL");

        // then
        assertThat(result).isEqualTo(expected);
    }
}
