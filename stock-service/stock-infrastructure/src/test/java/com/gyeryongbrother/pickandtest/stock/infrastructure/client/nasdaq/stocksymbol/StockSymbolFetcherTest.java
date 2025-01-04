package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.stocksymbol;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolResponseFixture.stockSymbolResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@DisplayName("주식 티커들을 가져온다")
class StockSymbolFetcherTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private StockSymbolFetcher stockSymbolFetcher;

    @BeforeEach
    void setUp() {
        stockSymbolFetcher = new StockSymbolFetcher(new NasdaqUrlProvider(), new HeaderProvider(), fetcherSupport);
    }

    @Test
    @DisplayName("주식 티커들을 가져온다")
    void fetchStockSymbol() {
        // given
        StockSymbolResponse stockSymbolResponse = stockSymbolResponse();
        given(fetcherSupport.get(anyString(), any(), any()))
                .willReturn(ResponseEntity.ok(stockSymbolResponse));

        // when
        StockSymbolResponse result = stockSymbolFetcher.fetchStockSymbol(NASDAQ);

        // then
        assertThat(result).isEqualTo(stockSymbolResponse);
    }
}
