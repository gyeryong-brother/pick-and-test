package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneThousand;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividend;
import static com.gyeryongbrother.pickandtest.domain.core.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주식과 주가와 배당에 관련된 기능을 제공한다")
class StockDetailTest {

    @Test
    @DisplayName("가장 최근의 주가를 가져온다")
    void getLastStockPrice() {
        // given
        StockDetail stockDetail = stockDetail(null, stockPrices(null), List.of());
        BigDecimal expected = BigDecimal.valueOf(200);

        // when
        BigDecimal result = stockDetail.getLastStockPrice();

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주가 데이터가 없으면 주가는 0 이다")
    void getLastStockPriceWhenNoExist() {
        // given
        StockDetail stockDetail = stockDetail(null, List.of(), List.of());

        // when
        BigDecimal result = stockDetail.getLastStockPrice();

        // then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("5년 기준 연평균 수익률을 계산한다")
    void calculateCompoundAnnualGrowthRate() {
        // given
        StockPrice targetStockPrice = stockPrice(LocalDate.of(2019, 1, 2), oneHundred());
        StockPrice lastStockPrice = stockPrice(LocalDate.of(2024, 1, 1), oneThousand());
        List<StockPrice> stockPrices = List.of(
                stockPrice(LocalDate.of(2018, 12, 31), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 1), twoHundred()),
                targetStockPrice,
                stockPrice(LocalDate.of(2019, 1, 3), threeHundred()),
                lastStockPrice
        );
        StockDetail stockDetail = stockDetail(null, stockPrices, List.of());
        BigDecimal expected = lastStockPrice.calculateCompoundAnnualGrowthRate(targetStockPrice);

        // when
        BigDecimal result = stockDetail.calculateCompoundAnnualGrowthRate();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("배당률을 계산한다")
    void calculateDividendYield() {
        // given
        List<Dividend> dividends = List.of(
                dividend(LocalDate.of(2023, 3, 1), BigDecimal.valueOf(0.1)),
                dividend(LocalDate.of(2023, 6, 1), BigDecimal.valueOf(0.2)),
                dividend(LocalDate.of(2023, 9, 1), BigDecimal.valueOf(0.3)),
                dividend(LocalDate.of(2023, 12, 1), BigDecimal.valueOf(0.4)),
                dividend(LocalDate.of(2024, 3, 1), BigDecimal.valueOf(0.5))
        );
        StockDetail stockDetail = stockDetail(null, stockPrices(null), dividends);
        BigDecimal expected = BigDecimal.valueOf(1);

        // when
        BigDecimal result = stockDetail.calculateDividendYield();

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주가 데이터가 없을 때 배당률은 0 이다")
    void calculateDividendYieldWhenStockPriceNoExist() {
        // given
        StockDetail stockDetail = stockDetail(null, List.of(), List.of());

        // when
        BigDecimal result = stockDetail.calculateDividendYield();

        // then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }
}
