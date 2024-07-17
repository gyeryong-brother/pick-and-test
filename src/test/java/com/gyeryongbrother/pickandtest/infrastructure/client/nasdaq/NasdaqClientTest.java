package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.StockExchangeFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockExchangeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NasdaqClientTest {

    @Mock
    private StockExchangeFetcher stockExchangeFetcher;

    private NasdaqClient nasdaqClient;

    @BeforeEach
    void setUp() {
        nasdaqClient = new NasdaqClient(stockExchangeFetcher);
    }

    @Test
    void fetchStockExchange() {
        // given
        StockExchangeResponse stockExchangeResponse = new StockExchangeResponse(null);
        given(stockExchangeFetcher.fetchStockExchange(any()))
                .willReturn(stockExchangeResponse);

        // when
        StockExchangeResponse result = nasdaqClient.fetchStockExchange(StockExchange.NASDAQ);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(stockExchangeResponse);
    }
}
