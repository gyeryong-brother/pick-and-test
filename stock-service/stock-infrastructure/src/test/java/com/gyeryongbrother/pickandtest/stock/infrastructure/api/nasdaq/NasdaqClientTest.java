package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.dto.StockSymbolResponseFixture.stockSymbolResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.DividendFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.StockSymbolFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.dto.StockSymbolResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Nasdaq api 를 사용한다")
class NasdaqClientTest {

    @Mock
    private StockSymbolFetcher stockSymbolFetcher;

    @Mock
    private DividendFetcher dividendFetcher;

    private NasdaqClient nasdaqClient;

    @BeforeEach
    void setUp() {
        nasdaqClient = new NasdaqClient(stockSymbolFetcher, dividendFetcher);
    }

    @Test
    @DisplayName("주식 티커들을 가져온다")
    void fetchStockExchange() {
        // given
        StockSymbolResponse stockSymbolResponse = stockSymbolResponse();
        given(stockSymbolFetcher.fetchStockSymbol(any()))
                .willReturn(stockSymbolResponse);

        // when
        StockSymbolResponse result = nasdaqClient.fetchStockSymbol(NASDAQ);

        // then
        assertThat(result).isEqualTo(stockSymbolResponse);
    }

    @Test
    @DisplayName("배당 정보를 가져온다")
    void fetchDividend() {
        // given
        DividendResponse appleDividendResponse = appleDividendResponse();
        given(dividendFetcher.fetchDividend(anyString(), any()))
                .willReturn(appleDividendResponse);

        // when
        DividendResponse result = nasdaqClient.fetchDividend("AAPL", "stocks");

        // then
        assertThat(result).isEqualTo(appleDividendResponse);
    }
}
