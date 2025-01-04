package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.FetchType.STOCK;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.appleStockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.KoreaInvestmentUrlProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@DisplayName("주식을 가져온다")
class StockFetcherTest {

    @Mock
    private HeaderHandler headerHandler;

    @Mock
    private FetcherSupport fetcherSupport;

    private StockFetcher stockFetcher;

    @BeforeEach
    void setUp() {
        stockFetcher = new StockFetcher(new KoreaInvestmentUrlProvider(), headerHandler, fetcherSupport);
    }

    @Test
    @DisplayName("주식을 가져온다")
    void fetchStock() {
        // given
        StockResponse appleStockResponse = appleStockResponse();
        given(headerHandler.getHeader(STOCK))
                .willReturn(new HttpHeaders());
        given(fetcherSupport.get(anyString(), any(HttpHeaders.class), any()))
                .willReturn(ResponseEntity.ok(appleStockResponse));

        // when
        StockResponse result = stockFetcher.fetchStock(NASDAQ_CODE, "AAPL");

        // then
        assertThat(result).isEqualTo(appleStockResponse);
    }
}
