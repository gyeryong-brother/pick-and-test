package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;

public record CreateFavoriteStockResponse(
        Long id,
        Long memberId,
        Long stockId
) {

    public static CreateFavoriteStockResponse from(FavoriteStock favoriteStock) {
        return new CreateFavoriteStockResponse(
                favoriteStock.getId(),
                favoriteStock.getMemberId(),
                favoriteStock.getStockDetail().getStock().getId()
        );
    }
}
