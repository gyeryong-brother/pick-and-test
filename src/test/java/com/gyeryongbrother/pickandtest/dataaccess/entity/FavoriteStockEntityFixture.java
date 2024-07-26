package com.gyeryongbrother.pickandtest.dataaccess.entity;

public class FavoriteStockEntityFixture {

    public static FavoriteStockEntity favoriteStockEntity(StockEntity stockEntity) {
        return FavoriteStockEntity.builder()
                .memberId(1L)
                .stock(stockEntity)
                .build();
    }
}
