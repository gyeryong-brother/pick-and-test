package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.common.StockServiceUrlProvider;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock.dto.StockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("주식을 가져온다")
class StockFetcherTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private StockFetcher stockFetcher;

    @BeforeEach
    void setUp() {
        stockFetcher = new StockFetcher(new StockServiceUrlProvider(), fetcherSupport);
    }

    @Test
    @DisplayName("주식을 가져온다")
    void fetchStock() {
        // given
        given(fetcherSupport.get(anyString(), any()))
                .willReturn(new StockResponse(1L, "AAPL", "NASDAQ"));
        StockResponse expected = new StockResponse(1L, "AAPL", "NASDAQ");

        // when
        StockResponse result = stockFetcher.fetchStock(1L);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
