package com.gyeryongbrother.pickandtest.domain.service.dto;

public record CreateFavoriteStockCommand(
        Long memberId,
        Long stockId
) {
}
