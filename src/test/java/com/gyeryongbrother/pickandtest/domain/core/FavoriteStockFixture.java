package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneThousand;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividend;
import static com.gyeryongbrother.pickandtest.domain.core.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FavoriteStockFixture {

    public static FavoriteStock favoriteStock(Long id) {
        StockDetail stockDetail = StockDetail.builder()
                .stock(stock(1L))
                .build();
        return FavoriteStock.builder()
                .id(id)
                .memberId(1L)
                .stockDetail(stockDetail)
                .build();
    }

    public static FavoriteStock favoriteStock(Stock stock) {
        StockDetail stockDetail = StockDetail.builder()
                .stock(stock)
                .build();
        return FavoriteStock.builder()
                .memberId(1L)
                .stockDetail(stockDetail)
                .build();
    }

    public static List<FavoriteStock> favoriteStocks(Long... stockIds) {
        return Arrays.stream(stockIds)
                .map(StockDetailFixture::stockDetail)
                .map(FavoriteStockFixture::favoriteStock)
                .toList();
    }

    private static FavoriteStock favoriteStock(StockDetail stockDetail) {
        return FavoriteStock.builder()
                .memberId(1L)
                .stockDetail(stockDetail)
                .build();
    }

    public static List<FavoriteStock> favoriteStocks() {
        List<StockPrice> stockPrices = List.of(
                stockPrice(LocalDate.of(2018, 12, 31), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 1), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 2), oneHundred()),
                stockPrice(LocalDate.of(2019, 1, 3), threeHundred()),
                stockPrice(LocalDate.of(2024, 1, 1), oneThousand())
        );
        List<Dividend> dividends = List.of(
                dividend(LocalDate.of(2023, 3, 1), BigDecimal.valueOf(0.1)),
                dividend(LocalDate.of(2023, 6, 1), BigDecimal.valueOf(0.2)),
                dividend(LocalDate.of(2023, 9, 1), BigDecimal.valueOf(0.3)),
                dividend(LocalDate.of(2023, 12, 1), BigDecimal.valueOf(0.4)),
                dividend(LocalDate.of(2024, 3, 1), BigDecimal.valueOf(0.5))
        );
        List<StockPrice> stockPrice = List.of(
                stockPrice(LocalDate.of(2024, 1, 1), oneHundred())
        );
        List<Dividend> dividend = List.of(
                dividend(LocalDate.of(2023, 3, 1), BigDecimal.valueOf(0.1)),
                dividend(LocalDate.of(2024, 3, 1), BigDecimal.valueOf(0.5))
        );
        return List.of(
                favoriteStock(stockDetail(null, stockPrices, dividends)),
                favoriteStock(stockDetail(null, stockPrice, dividend))
        );
    }
}
