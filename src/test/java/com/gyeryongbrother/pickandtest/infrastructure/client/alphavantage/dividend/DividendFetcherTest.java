package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common.AlphaVantageClientCredential;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common.AlphaVantageUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("배당 정보를 가져온다")
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
    @DisplayName("배당 정보를 가져온다")
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
