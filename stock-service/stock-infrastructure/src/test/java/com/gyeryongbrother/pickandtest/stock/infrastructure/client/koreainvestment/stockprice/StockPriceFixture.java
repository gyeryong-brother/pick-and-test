package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StockPriceFixture {

    public static StockPrice stockPrice(int day, double price) {
        return StockPrice.builder()
                .date(LocalDate.of(2024, 7, day))
                .price(BigDecimal.valueOf(price))
                .build();
    }

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

    public static List<StockPrice> microsoftStockPrices() {
        return List.of(
                stockPrice(12, 453.55),
                stockPrice(11, 454.7),
                stockPrice(10, 466.25),
                stockPrice(9, 459.54),
                stockPrice(8, 466.24),
                stockPrice(5, 467.56),
                stockPrice(3, 460.77),
                stockPrice(2, 459.28)
        );
    }

    public static List<StockPrice> nvidiaStockPrices() {
        return List.of(
                stockPrice(12, 129.24),
                stockPrice(11, 127.4),
                stockPrice(10, 134.91),
                stockPrice(9, 131.38),
                stockPrice(8, 128.2),
                stockPrice(5, 125.83),
                stockPrice(3, 128.28),
                stockPrice(2, 122.67)
        );
    }
}
