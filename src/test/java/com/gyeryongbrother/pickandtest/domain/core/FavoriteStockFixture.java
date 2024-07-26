package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;

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
}
