package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("nasdaq api 에서 사용되는 돈 포맷에 맞춰 타입을 변환한다")
class MoneyHandlerTest {

    @Test
    @DisplayName("api 응답 데이터를 BigDecimal 로 변환한다")
    void toBigDecimal() {
        // given
        BigDecimal expected = BigDecimal.valueOf(0.1);

        // when
        BigDecimal result = MoneyHandler.toBigDecimal("$0.10");

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
