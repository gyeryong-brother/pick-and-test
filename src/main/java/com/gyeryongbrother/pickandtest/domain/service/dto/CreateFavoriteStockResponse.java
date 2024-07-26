package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;

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
