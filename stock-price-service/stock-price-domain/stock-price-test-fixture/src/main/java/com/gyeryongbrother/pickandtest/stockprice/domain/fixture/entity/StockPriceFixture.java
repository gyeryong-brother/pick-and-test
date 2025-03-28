package com.gyeryongbrother.pickandtest.stockprice.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.LocalDateFixture.januarySecond;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class StockPriceFixture {

    public static List<StockPrice> januaryStockPrices() {
        return List.of(
                stockPrice(LocalDate.of(2024, 1, 1), BigDecimal.valueOf(100)),
                stockPrice(LocalDate.of(2024, 1, 2), BigDecimal.valueOf(200)),
                stockPrice(LocalDate.of(2024, 1, 3), BigDecimal.valueOf(300))
        );
    }

    private static StockPrice stockPrice(LocalDate date, BigDecimal price) {
        return new StockPrice(null, null, date, price);
    }

    public static List<StockPrice> stockPrices(Long stockId) {
        return List.of(
                stockPrice(stockId, januaryFirst(), oneHundred()),
                stockPrice(stockId, januarySecond(), twoHundred())
        );
    }

    private static StockPrice stockPrice(Long stockId, LocalDate date, BigDecimal price) {
        return new StockPrice(null, stockId, date, price);
    }

    public static Map<Long, List<StockPrice>> stockPricesByStockId() {
        return Map.of(
                1L, List.of(
                        new StockPrice(null, 1L, januaryFirst(), oneHundred()),
                        new StockPrice(null, 1L, januarySecond(), twoHundred())
                )
        );
    }
}
