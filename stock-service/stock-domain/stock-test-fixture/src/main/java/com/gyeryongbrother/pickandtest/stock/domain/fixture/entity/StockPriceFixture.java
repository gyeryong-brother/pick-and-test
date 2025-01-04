package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneThousand;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januarySecond;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class StockPriceFixture {

    public static List<StockPrice> appleStockPrices() {
        return List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98),
                stockPrice(9, 228.68),
                stockPrice(8, 227.82),
                stockPrice(5, 226.34),
                stockPrice(3, 221.55),
                stockPrice(2, 220.27)
        );
    }

    public static StockPrice stockPrice(int day, double price) {
        return StockPrice.builder()
                .date(LocalDate.of(2024, 7, day))
                .price(BigDecimal.valueOf(price))
                .build();
    }

    public static List<StockPrice> stockPrices() {
        return List.of(
                stockPrice(LocalDate.of(2018, 12, 31), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 1), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 2), oneHundred()),
                stockPrice(LocalDate.of(2019, 1, 3), threeHundred()),
                stockPrice(LocalDate.of(2024, 1, 1), oneThousand())
        );
    }

    public static List<StockPrice> januaryStockPrices() {
        return List.of(
                stockPrice(LocalDate.of(2024, 1, 1), BigDecimal.valueOf(100)),
                stockPrice(LocalDate.of(2024, 1, 2), BigDecimal.valueOf(200)),
                stockPrice(LocalDate.of(2024, 1, 3), BigDecimal.valueOf(300))
        );
    }

    public static StockPrice stockPrice() {
        return stockPrice(januaryFirst(), oneHundred());
    }

    public static List<StockPrice> stockPrices(Long stockId) {
        return List.of(
                stockPrice(stockId, januaryFirst(), oneHundred()),
                stockPrice(stockId, januarySecond(), twoHundred())
        );
    }

    public static StockPrice stockPrice(Long stockId, LocalDate date, BigDecimal price) {
        return StockPrice.builder()
                .stockId(stockId)
                .date(date)
                .price(price)
                .build();
    }

    public static StockPrice stockPrice(LocalDate date, BigDecimal price) {
        return StockPrice.builder()
                .date(date)
                .price(price)
                .build();
    }
}
