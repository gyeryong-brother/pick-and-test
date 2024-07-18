package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockSymbolResponseFixture.stockSymbolResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.StockExchangeFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockSymbolResponse;
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
        StockSymbolResponse stockSymbolResponse = stockSymbolResponse();
        given(stockExchangeFetcher.fetchStockSymbol(any()))
                .willReturn(stockSymbolResponse);

        // when
        StockSymbolResponse result = nasdaqClient.fetchStockSymbol(NASDAQ);

        // then
        assertThat(result).isEqualTo(stockSymbolResponse);
    }
}
