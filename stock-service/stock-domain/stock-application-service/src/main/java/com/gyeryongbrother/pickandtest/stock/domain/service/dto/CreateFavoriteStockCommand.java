package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;

public record CreateFavoriteStockCommand(
        Long stockId,
        Long memberId
) {

    public FavoriteStock toDomain() {
        return FavoriteStock.builder()
                .memberId(memberId)
                .stock(stock())
                .build();
    }

    private Stock stock() {
        return Stock.builder()
                .id(stockId)
                .build();
    }
}
