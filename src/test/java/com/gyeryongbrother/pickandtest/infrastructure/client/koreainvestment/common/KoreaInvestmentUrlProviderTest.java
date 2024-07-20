package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.Period.DAY;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KoreaInvestmentUrlProviderTest {

    private KoreaInvestmentUrlProvider koreaInvestmentUrlProvider;

    @BeforeEach
    void setUp() {
        koreaInvestmentUrlProvider = new KoreaInvestmentUrlProvider();
    }

    @Test
    void getStockEndPoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/search-info?PRDT_TYPE_CD=512&PDNO=AAPL";

        // when
        String result = koreaInvestmentUrlProvider.getStockEndpoint(NASDAQ_CODE, "AAPL");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getStockPriceEndPoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443"
                + "/uapi/overseas-price/v1/quotations/daily-price"
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

    @Test
    void getTokenEndpoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443/oauth2/tokenP";

        // when
        String result = koreaInvestmentUrlProvider.getTokenEndpoint();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
