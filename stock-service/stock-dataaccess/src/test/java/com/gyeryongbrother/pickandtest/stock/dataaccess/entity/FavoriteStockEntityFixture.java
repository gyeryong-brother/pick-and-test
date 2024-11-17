package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

public class FavoriteStockEntityFixture {

    public static FavoriteStockEntity favoriteStockEntity(StockEntity stockEntity) {
        return FavoriteStockEntity.builder()
                .memberId(1L)
                .stock(stockEntity)
                .build();
    }
}
