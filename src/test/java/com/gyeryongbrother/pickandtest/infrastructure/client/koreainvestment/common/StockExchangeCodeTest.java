package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.KOSPI;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("api 에서 사용되는 거래소 관련 코드를 제공한다")
class StockExchangeCodeTest {

    @Test
    @DisplayName("주식거래소로 부터 생성할 수 있다")
    void from() {
        // when
        StockExchangeCode result = StockExchangeCode.from(NASDAQ);

        // then
        assertThat(result).isEqualTo(NASDAQ_CODE);
    }

    @Test
    @DisplayName("지원하지 않는 거래소면 예외가 발생한다")
    void fromFail() {
        // when
        String result = assertThrows(IllegalArgumentException.class, () ->
                StockExchangeCode.from(KOSPI)
        ).getMessage();

        // then
        assertThat(result).isEqualTo("not supported stock exchange");
    }
}
