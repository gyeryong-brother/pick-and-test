package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UrlProviderTest {

    private UrlProvider urlProvider;

    @BeforeEach
    void setUp() {
        urlProvider = new UrlProvider();
    }

    @Test
    void getStockEndPoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/search-info?PRDT_TYPE_CD=512&PDNO=AAPL";

        // when
        String result = urlProvider.getStockEndpoint(StockExchange.NASDAQ, "AAPL");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getTokenEndpoint() {
        // given
        String expected = "https://openapi.koreainvestment.com:9443/oauth2/tokenP";

        // when
        String result = urlProvider.getTokenEndpoint();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
