package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPriceFixture.stockPrices;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.oneThousand;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.twoHundred;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주가들과 관련된 기능을 제공한다")
class StockPricesTest {

    @Test
    @DisplayName("가장 최근의 주가를 가져온다")
    void getLastStockPrice() {
        // given
        StockPrices stockPrices = StockPrices.from(stockPrices(null));
        BigDecimal expected = twoHundred();

        // when
        BigDecimal result = stockPrices.getLastStockPrice();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("주가 데이터가 없으면 주가는 0 이다")
    void getLastStockPriceWhenNoExist() {
        // given
        StockPrices stockPrices = StockPrices.from(List.of());

        // when
        BigDecimal result = stockPrices.getLastStockPrice();

        // then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("5년 기준 연평균 수익률을 계산한다")
    void calculateCompoundAnnualGrowthRate() {
        // given
        StockPrice targetStockPrice = stockPrice(LocalDate.of(2019, 1, 2), oneHundred());
        StockPrice lastStockPrice = stockPrice(LocalDate.of(2024, 1, 1), oneThousand());
        StockPrices stockPrices = StockPrices.from(List.of(
                stockPrice(LocalDate.of(2018, 12, 31), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 1), twoHundred()),
                targetStockPrice,
                stockPrice(LocalDate.of(2019, 1, 3), threeHundred()),
                lastStockPrice
        ));
        BigDecimal expected = lastStockPrice.calculateCompoundAnnualGrowthRate(targetStockPrice);

        // when
        BigDecimal result = stockPrices.calculateCompoundAnnualGrowthRate();

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("5년전 데이터가 없으면 가장 과거의 주가로 계산한다")
    void calculateCompoundAnnualGrowthRateWhenOnlyFourYearsAgoExist() {
        // given
        StockPrice targetStockPrice = stockPrice(LocalDate.of(2020, 1, 1), oneHundred());
        StockPrice lastStockPrice = stockPrice(LocalDate.of(2024, 1, 1), oneThousand());
        StockPrices stockPrices = StockPrices.from(List.of(
                targetStockPrice,
                stockPrice(LocalDate.of(2020, 1, 2), twoHundred()),
                stockPrice(LocalDate.of(2020, 1, 3), threeHundred()),
                lastStockPrice
        ));
        BigDecimal expected = lastStockPrice.calculateCompoundAnnualGrowthRate(targetStockPrice);

        // when
        BigDecimal result = stockPrices.calculateCompoundAnnualGrowthRate();

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("가장 과거의 주가가 가장 최근의 주가와 같은 연도면 연평균 수익률은 0 이다")
    void calculateCompoundAnnualGrowthRateWhenLatestIsSameYear() {
        // given
        StockPrice targetStockPrice = stockPrice(LocalDate.of(2024, 1, 1), oneHundred());
        StockPrice lastStockPrice = stockPrice(LocalDate.of(2024, 1, 4), oneThousand());
        StockPrices stockPrices = StockPrices.from(List.of(
                targetStockPrice,
                stockPrice(LocalDate.of(2020, 1, 2), twoHundred()),
                stockPrice(LocalDate.of(2020, 1, 3), threeHundred()),
                lastStockPrice
        ));

        // when
        BigDecimal result = stockPrices.calculateCompoundAnnualGrowthRate();

        // then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("주가 데이터가 없으면 연평균 수익률은 0 이다")
    void calculateCompoundAnnualGrowthRateWhenNoExist() {
        // given
        StockPrices stockPrices = StockPrices.from(List.of());

        // when
        BigDecimal result = stockPrices.calculateCompoundAnnualGrowthRate();

        // then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }
}
