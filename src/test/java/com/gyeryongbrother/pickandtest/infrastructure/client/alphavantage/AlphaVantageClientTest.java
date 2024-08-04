package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage;

import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Alpha Vantage api 를 사용한다")
class AlphaVantageClientTest {

    @Mock
    private DividendFetcher dividendFetcher;

    private AlphaVantageClient alphaVantageClient;

    @BeforeEach
    void setUp() {
        alphaVantageClient = new AlphaVantageClient(dividendFetcher);
    }

    @Test
    @DisplayName("배당 정보를 가져온다")
    void fetchDividend() {
        // given
        DividendResponse appleDividendResponse = appleDividendResponse();
        given(dividendFetcher.fetchDividend(anyString()))
                .willReturn(appleDividendResponse);

        // when
        DividendResponse result = alphaVantageClient.fetchDividend("AAPL");

        // then
        assertThat(result).isEqualTo(appleDividendResponse);
    }
}
