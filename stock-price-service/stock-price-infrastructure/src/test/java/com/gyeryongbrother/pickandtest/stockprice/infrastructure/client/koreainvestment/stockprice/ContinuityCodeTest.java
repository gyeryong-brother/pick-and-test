package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.INVALID_CONTINUITY_CODE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("다음 응답 존재 여부를 나타낸다")
class ContinuityCodeTest {

    @ParameterizedTest
    @CsvSource(value = {"F", "M"})
    @DisplayName("다음 응답 코드가 F 나 M 이면 NEXT 를 반환한다")
    void createNext(String code) {
        // when
        ContinuityCode result = ContinuityCode.from(code);

        // then
        assertThat(result).isEqualTo(ContinuityCode.NEXT);
    }

    @ParameterizedTest
    @CsvSource(value = {"D", "E"})
    @DisplayName("다음 응답 코드가 D 나 E 면 END 를 반환한다")
    void createEnd(String code) {
        // when
        ContinuityCode result = ContinuityCode.from(code);

        // then
        assertThat(result).isEqualTo(ContinuityCode.END);
    }

    @Test
    @DisplayName("지원하지 않는 코드면 예외가 발생한다")
    void createByInvalidCode() {
        // when
        BaseExceptionType result = assertThrows(StockPriceInfrastructureException.class, () ->
                ContinuityCode.from("A")
        ).exceptionType();

        // then
        assertThat(result).isEqualTo(INVALID_CONTINUITY_CODE);
    }

    @Test
    @DisplayName("NEXT 는 다음 응답이 존재한다")
    void hasNextTrue() {
        // when
        boolean result = ContinuityCode.NEXT.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("END 는 다음 응답이 존재하지 않는다")
    void hasNextFalse() {
        // when
        boolean result = ContinuityCode.END.hasNext();

        // then
        assertThat(result).isFalse();
    }
}
