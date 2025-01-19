package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

public record DeleteFavoriteStockCommand(
        Long favoriteStockId,
        Long memberId
) {
}
