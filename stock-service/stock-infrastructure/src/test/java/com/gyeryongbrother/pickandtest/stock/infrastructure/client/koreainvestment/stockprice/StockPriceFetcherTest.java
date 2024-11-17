package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.FetchType.STOCK_PRICE;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.ContinuityCode.NEXT;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.Period.DAY;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.appleFirstStockPriceBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.KoreaInvestmentUrlProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@DisplayName("주가를 가져온다")
class StockPriceFetcherTest {

    @Mock
    private HeaderHandler headerHandler;

    @Mock
    private FetcherSupport fetcherSupport;

    private StockPriceFetcher stockPriceFetcher;

    @BeforeEach
    void setUp() {
        stockPriceFetcher = new StockPriceFetcher(new KoreaInvestmentUrlProvider(), headerHandler, fetcherSupport);
    }

    @Test
    @DisplayName("주가를 가져온다")
    void fetchStockPrice() {
        // given
        StockPriceBody appleFirstStockPriceBody = appleFirstStockPriceBody();
        given(headerHandler.getHeader(STOCK_PRICE))
                .willReturn(new HttpHeaders());
        given(fetcherSupport.get(anyString(), any(HttpHeaders.class), any()))
                .willReturn(ResponseEntity.ok(appleFirstStockPriceBody));
        given(headerHandler.parseContinuityCode(any(HttpHeaders.class)))
                .willReturn(NEXT);
        StockPriceResponse expected = new StockPriceResponse(NEXT, appleFirstStockPriceBody);

        // when
        StockPriceResponse result = stockPriceFetcher.fetchStockPrice(
                NASDAQ_CODE,
                "AAPL",
                DAY,
                LocalDate.of(2024, 1, 1)
        );

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
