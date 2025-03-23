package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.ContinuityCode.NEXT;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.Period.DAY;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.appleFirstStockPriceBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("한국투자증권 api 를 사용한다")
class KoreaInvestmentClientTest {

    @Mock
    private StockPriceFetcher stockPriceFetcher;

    private KoreaInvestmentClient koreaInvestmentClient;

    @BeforeEach
    void setUp() {
        koreaInvestmentClient = new KoreaInvestmentClient(stockPriceFetcher);
    }

    @Test
    @DisplayName("주가 정보를 가져온다")
    void fetchStockPrice() {
        // given
        LocalDate date = LocalDate.of(2024, 1, 1);
        StockPriceResponse stockPriceResponse = new StockPriceResponse(NEXT, appleFirstStockPriceBody());
        given(stockPriceFetcher.fetchStockPrice(NASDAQ_CODE, "AAPL", DAY, date))
                .willReturn(stockPriceResponse);

        // when
        StockPriceResponse result = koreaInvestmentClient.fetchStockPrice(NASDAQ, "AAPL", date);

        // then
        assertThat(result).isEqualTo(stockPriceResponse);
    }
}
