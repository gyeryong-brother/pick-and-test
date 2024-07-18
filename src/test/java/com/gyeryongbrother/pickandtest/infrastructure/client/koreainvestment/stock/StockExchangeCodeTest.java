package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.KOSPI;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchangeCode.NASDAQ_CODE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StockExchangeCodeTest {

    @Test
    void from() {
        // when
        StockExchangeCode result = StockExchangeCode.from(NASDAQ);

        // then
        assertThat(result).isEqualTo(NASDAQ_CODE);
    }

    @Test
    void fromFail() {
        // when
        String result = assertThrows(IllegalArgumentException.class, () ->
                StockExchangeCode.from(KOSPI)
        ).getMessage();

        // then
        assertThat(result).isEqualTo("not supported stock exchange");
    }
}
