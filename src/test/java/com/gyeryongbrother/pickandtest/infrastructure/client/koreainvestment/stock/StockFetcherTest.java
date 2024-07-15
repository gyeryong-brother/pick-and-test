package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.FetchType;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.UrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponseFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class StockFetcherTest {

    @Mock
    private HeaderHandler headerHandler;

    @Mock
    private FetcherSupport fetcherSupport;

    private StockFetcher stockFetcher;

    @BeforeEach
    void setUp() {
        stockFetcher = new StockFetcher(new UrlProvider(), headerHandler, fetcherSupport);
    }

    @Test
    void fetchStock() {
        // given
        FetchStockResponse fetchStockResponse = FetchStockResponseFixture.empty();
        given(headerHandler.getHeader(FetchType.STOCK))
                .willReturn(new HttpHeaders());
        given(fetcherSupport.get(anyString(), any(HttpHeaders.class), any()))
                .willReturn(ResponseEntity.ok(fetchStockResponse));

        // when
        FetchStockResponse result = stockFetcher.fetchStock(StockExchange.NASDAQ, "AAPL");

        // then
        assertThat(result).isEqualTo(fetchStockResponse);
    }
}
