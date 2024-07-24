package com.gyeryongbrother.pickandtest.domain.core;

public class FavoriteStockFixture {

    public static FavoriteStock favoriteStock(Stock stock) {
        return FavoriteStock.builder()
                .memberId(1L)
                .stock(stock)
                .build();
    }
}
