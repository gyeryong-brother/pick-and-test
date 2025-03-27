package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.FetchType.STOCK_PRICE;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.ContinuityCode.NEXT;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.appleFirstStockPriceBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.common.FetcherSupport;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.KoreaInvestmentUrlProvider;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto.StockPriceResponse;
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
@DisplayName("한국투자증권 api 를 사용한다")
class KoreaInvestmentClientTest {

    @Mock
    private HeaderHandler headerHandler;

    @Mock
    private FetcherSupport fetcherSupport;

    private KoreaInvestmentClient koreaInvestmentClient;

    @BeforeEach
    void setUp() {
        koreaInvestmentClient = new KoreaInvestmentClient(new KoreaInvestmentUrlProvider(), headerHandler,
                fetcherSupport);
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
        StockPriceResponse result = koreaInvestmentClient.fetchOneHundredStockPricesBeforeDate(
                NASDAQ,
                "AAPL",
                LocalDate.of(2024, 1, 1)
        );

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
