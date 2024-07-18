package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend;

import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common.AlphaVantageClientCredential;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common.AlphaVantageUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DividendFetcherTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private DividendFetcher dividendFetcher;

    @BeforeEach
    void setUp() {
        AlphaVantageClientCredential alphaVantageClientCredential = new AlphaVantageClientCredential("apiKey");
        AlphaVantageUrlProvider alphaVantageUrlProvider = new AlphaVantageUrlProvider(alphaVantageClientCredential);
        dividendFetcher = new DividendFetcher(alphaVantageUrlProvider, fetcherSupport);
    }

    @Test
    void fetchDividend() {
        // given
        DividendResponse appleDividendResponse = appleDividendResponse();
        given(fetcherSupport.get(anyString(), any()))
                .willReturn(appleDividendResponse);

        // when
        DividendResponse result = dividendFetcher.fetchDividend("AAPL");

        // then
        assertThat(result).isEqualTo(appleDividendResponse);
    }
}
