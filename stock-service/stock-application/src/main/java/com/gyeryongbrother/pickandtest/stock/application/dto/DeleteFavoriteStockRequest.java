package com.gyeryongbrother.pickandtest.stock.application.dto;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.DeleteFavoriteStockCommand;

public record DeleteFavoriteStockRequest(
        Long memberId
) {

    public DeleteFavoriteStockCommand toCommand(Long favoriteStockId) {
        return new DeleteFavoriteStockCommand(favoriteStockId, memberId);
    }
}
