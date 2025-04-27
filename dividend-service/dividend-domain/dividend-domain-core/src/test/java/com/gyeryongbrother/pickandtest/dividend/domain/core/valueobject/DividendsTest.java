package com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("배당들과 관련된 기능을 제공한다")
class DividendsTest {

    @Test
    @DisplayName("배당률을 계산한다")
    void calculateDividendYield() {
        // given
        Dividends dividends = Dividends.from(List.of(
                new Dividend(null, null, LocalDate.of(2023, 3, 1), BigDecimal.valueOf(0.1)),
                new Dividend(null, null, LocalDate.of(2023, 6, 1), BigDecimal.valueOf(0.2)),
                new Dividend(null, null, LocalDate.of(2023, 9, 1), BigDecimal.valueOf(0.3)),
                new Dividend(null, null, LocalDate.of(2023, 12, 1), BigDecimal.valueOf(0.4)),
                new Dividend(null, null, LocalDate.of(2024, 3, 1), BigDecimal.valueOf(0.5))
        ));
        BigDecimal expected = BigDecimal.valueOf(2);

        // when
        BigDecimal result = dividends.calculateDividendYield(BigDecimal.valueOf(100));

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("지난 연도 데이터가 없으면 배당률은 0 이다")
    void calculateDividendYieldWhenOneYearAgoNoExist() {
        // given
        Dividends dividends = Dividends.from(List.of(
                new Dividend(null, null, LocalDate.of(2024, 3, 1), BigDecimal.valueOf(0.5))
        ));

        // when
        BigDecimal result = dividends.calculateDividendYield(BigDecimal.valueOf(100));

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("배당 데이터가 없으면 배당률은 0 이다")
    void calculateDividendYieldWhenNoExist() {
        // given
        Dividends dividends = Dividends.from(List.of());

        // when
        BigDecimal result = dividends.calculateDividendYield(BigDecimal.valueOf(100));

        // then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }
}
