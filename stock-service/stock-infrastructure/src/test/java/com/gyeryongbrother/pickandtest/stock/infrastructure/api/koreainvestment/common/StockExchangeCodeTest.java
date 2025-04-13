package com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.KOSPI;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.common.StockExchangeCode.NASDAQ_CODE;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureExceptionType.NOT_SUPPORTED_STOCK_EXCHANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureException;
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
        BaseExceptionType result = assertThrows(StockInfrastructureException.class, () ->
                StockExchangeCode.from(KOSPI)
        ).exceptionType();

        // then
        assertThat(result).isEqualTo(NOT_SUPPORTED_STOCK_EXCHANGE);
    }
}
