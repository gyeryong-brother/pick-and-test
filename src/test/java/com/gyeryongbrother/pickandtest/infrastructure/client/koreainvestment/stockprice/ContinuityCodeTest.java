package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ContinuityCodeTest {

    @ParameterizedTest
    @CsvSource(value = {"F", "M"})
    void createNext(String code) {
        // when
        ContinuityCode result = ContinuityCode.from(code);

        // then
        assertThat(result).isEqualTo(ContinuityCode.NEXT);
    }

    @ParameterizedTest
    @CsvSource(value = {"D", "E"})
    void createEnd(String code) {
        // when
        ContinuityCode result = ContinuityCode.from(code);

        // then
        assertThat(result).isEqualTo(ContinuityCode.END);
    }

    @Test
    void createByInvalidCode() {
        // when
        String result = assertThrows(IllegalArgumentException.class, () ->
                ContinuityCode.from("A")
        ).getMessage();

        // then
        assertThat(result).isEqualTo("invalid code");
    }

    @Test
    void hasNextTrue() {
        // when
        boolean result = ContinuityCode.NEXT.hasNext();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void hasNextFalse() {
        // when
        boolean result = ContinuityCode.END.hasNext();

        // then
        assertThat(result).isFalse();
    }
}
