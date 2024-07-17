package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.FetchType;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.UrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class StockPriceFetcherTest {

    @Mock
    private HeaderHandler headerHandler;

    @Mock
    private FetcherSupport fetcherSupport;

    private StockPriceFetcher stockPriceFetcher;

    @BeforeEach
    void setUp() {
        stockPriceFetcher = new StockPriceFetcher(new UrlProvider(), headerHandler, fetcherSupport);
    }

    @Test
    void fetchStockPrice() {
        // given
        StockPriceBody stockPriceBody = StockPriceBodyFixture.empty();
        given(headerHandler.getHeader(FetchType.STOCK_PRICE))
                .willReturn(new HttpHeaders());
        given(fetcherSupport.get(anyString(), any(HttpHeaders.class), any()))
                .willReturn(ResponseEntity.ok(stockPriceBody));
        given(headerHandler.parseContinuityCode(any(HttpHeaders.class)))
                .willReturn(ContinuityCode.NEXT);
        StockPriceResponse expected = new StockPriceResponse(ContinuityCode.NEXT, stockPriceBody);

        // when
        StockPriceResponse result = stockPriceFetcher.fetchStockPrice(
                StockExchange.NASDAQ,
                "AAPL",
                Period.DAY,
                LocalDate.of(2024, 1, 1)
        );

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
