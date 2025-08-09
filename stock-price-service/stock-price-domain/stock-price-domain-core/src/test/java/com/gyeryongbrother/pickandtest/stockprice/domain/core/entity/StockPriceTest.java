package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.oneThousand;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.LocalDateFixture.twentyTwenty;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.LocalDateFixture.twentyTwentyFour;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주가와 관련된 기능을 제공한다")
class StockPriceTest {

    @Test
    @DisplayName("과거의 주가를 받아 연평균 수익률을 계산한다")
    void calculateCompoundAnnualGrowthRateByPast() {
        // given
        StockPrice twentyTwentyFourStockPrice = stockPrice(twentyTwentyFour(), oneThousand());
        StockPrice twentyTwentyStockPrice = stockPrice(twentyTwenty(), oneHundred());
        BigDecimal expected = BigDecimal.valueOf(77.83);

        // when
        BigDecimal result = twentyTwentyFourStockPrice.calculateCompoundAnnualGrowthRate(twentyTwentyStockPrice);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("미래의 주가를 받아 연평균 수익률을 계산한다")
    void calculateCompoundAnnualGrowthRateByFuture() {
        // given
        StockPrice twentyTwentyStockPrice = stockPrice(twentyTwenty(), oneHundred());
        StockPrice twentyTwentyFourStockPrice = stockPrice(twentyTwentyFour(), oneThousand());
        BigDecimal expected = BigDecimal.valueOf(77.83);

        // when
        BigDecimal result = twentyTwentyStockPrice.calculateCompoundAnnualGrowthRate(twentyTwentyFourStockPrice);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("같은 연도의 주가를 받으면 예외가 발생한다")
    void calculateCompoundAnnualGrowthRateBySameYear() {
        StockPrice twentyTwentyStockPrice = stockPrice(twentyTwenty(), oneHundred());

        // when
        BigDecimal result = twentyTwentyStockPrice.calculateCompoundAnnualGrowthRate(twentyTwentyStockPrice);

        // then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("가격이 감소하는 경우 연평균 수익률은 음수이다")
    void calculateCompoundAnnualGrowthRateByDecrease() {
        // given
        StockPrice twentyTwentyStockPrice = stockPrice(twentyTwenty(), oneThousand());
        StockPrice twentyTwentyFourStockPrice = stockPrice(twentyTwentyFour(), oneHundred());
        BigDecimal expected = BigDecimal.valueOf(-43.77);

        // when
        BigDecimal result = twentyTwentyStockPrice.calculateCompoundAnnualGrowthRate(twentyTwentyFourStockPrice);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
