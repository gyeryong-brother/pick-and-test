package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;

public record CreateFavoriteStockCommand(
        Long memberId,
        Long stockId
) {

    public FavoriteStock toDomain() {
        Stock stock = Stock.builder()
                .id(stockId)
                .build();
        return FavoriteStock.builder()
                .memberId(memberId)
                .stock(stock)
                .build();
    }
}
