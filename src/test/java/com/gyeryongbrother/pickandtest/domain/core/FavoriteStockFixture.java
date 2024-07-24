package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;

public class FavoriteStockFixture {

    public static FavoriteStock favoriteStock(Long id) {
        return FavoriteStock.builder()
                .id(id)
                .memberId(1L)
                .stock(stock(1L))
                .build();
    }

    public static FavoriteStock favoriteStock(Stock stock) {
        return FavoriteStock.builder()
                .memberId(1L)
                .stock(stock)
                .build();
    }
}
