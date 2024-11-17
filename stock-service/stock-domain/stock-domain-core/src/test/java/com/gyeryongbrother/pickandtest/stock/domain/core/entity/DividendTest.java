package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.DividendFixture.dividend;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.LocalDateFixture.twentyTwentyFour;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("배당과 관련된 기능을 제공한다")
class DividendTest {

    @Test
    @DisplayName("1년 전의 연도를 가져온다")
    void getYearOneYearAgo() {
        // given
        Dividend dividend = dividend(twentyTwentyFour());
        int expected = 2023;

        // when
        int result = dividend.getYearOneYearAgo();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("같은 연도면 참을 반환한다")
    void hasSameYearTrue() {
        // given
        Dividend dividend = dividend(twentyTwentyFour());

        // when
        boolean result = dividend.hasSameYear(2024);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("같은 연도가 아니면 거짓을 반환한다")
    void hasSameYearFalse() {
        // given
        Dividend dividend = dividend(twentyTwentyFour());

        // when
        boolean result = dividend.hasSameYear(2023);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("배당률을 계산한다")
    void calculateDividendYield() {
        // given
        Dividend dividend = dividend(BigDecimal.valueOf(0.24));
        BigDecimal expected = BigDecimal.valueOf(1.43);

        // when
        BigDecimal result = dividend.calculateDividendYield(BigDecimal.valueOf(4), BigDecimal.valueOf(67));

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
