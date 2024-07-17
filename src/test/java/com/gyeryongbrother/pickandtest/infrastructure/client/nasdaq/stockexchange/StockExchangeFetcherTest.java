package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockExchangeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class StockExchangeFetcherTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private StockExchangeFetcher stockExchangeFetcher;

    @BeforeEach
    void setUp() {
        stockExchangeFetcher = new StockExchangeFetcher(new NasdaqUrlProvider(), new HeaderProvider(), fetcherSupport);
    }

    @Test
    void fetchStockExchange() {
        // given
        StockExchangeResponse stockExchangeResponse = new StockExchangeResponse(null);
        given(fetcherSupport.get(anyString(), any(), any()))
                .willReturn(ResponseEntity.ok(stockExchangeResponse));

        // when
        StockExchangeResponse result = stockExchangeFetcher.fetchStockExchange(StockExchange.NASDAQ);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(stockExchangeResponse);
    }
}
