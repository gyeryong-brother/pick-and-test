package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlphaVantageClientTest {

    @Mock
    private DividendFetcher dividendFetcher;

    private AlphaVantageClient alphaVantageClient;

    @BeforeEach
    void setUp() {
        alphaVantageClient = new AlphaVantageClient(dividendFetcher);
    }

    @Test
    void fetchDividend() {
        // given
        DividendResponse dividendResponse = DividendResponseFixture.empty();
        given(dividendFetcher.fetchDividend(anyString()))
                .willReturn(dividendResponse);

        // when
        DividendResponse result = alphaVantageClient.fetchDividend("AAPL");

        // then
        assertThat(result).isEqualTo(dividendResponse);
    }
}
