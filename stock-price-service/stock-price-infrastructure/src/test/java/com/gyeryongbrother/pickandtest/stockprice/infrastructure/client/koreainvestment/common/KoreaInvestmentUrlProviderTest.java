package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.Period.DAY;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("한국투자증권 api url 을 제공한다")
class KoreaInvestmentUrlProviderTest {

    private KoreaInvestmentUrlProvider koreaInvestmentUrlProvider;

    @BeforeEach
    void setUp() {
        koreaInvestmentUrlProvider = new KoreaInvestmentUrlProvider();
    }

    @Test
    @DisplayName("주가 api endpoint 를 가져온다")
    void getStockPriceEndPoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443"
                + "/uapi/overseas-price/v1/quotations/dailyprice"
                + "?AUTH=&EXCD=NAS&SYMB=AAPL&GUBN=0&BYMD=20240101&MODP=1";

        // when
        String result = koreaInvestmentUrlProvider.getStockPriceEndpoint(
                NASDAQ_CODE,
                "AAPL",
                DAY,
                LocalDate.of(2024, 1, 1)
        );

        // then
        assertThat(result).isEqualTo(expected);
    }
}
