package com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("주식 api endpoint 를 가져온다")
    void getStockEndPoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/search-info?PRDT_TYPE_CD=512&PDNO=AAPL";

        // when
        String result = koreaInvestmentUrlProvider.getStockEndpoint(NASDAQ_CODE, "AAPL");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("액세스 토큰 api endpoint 를 가져온다")
    void getTokenEndpoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443/oauth2/tokenP";

        // when
        String result = koreaInvestmentUrlProvider.getTokenEndpoint();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
