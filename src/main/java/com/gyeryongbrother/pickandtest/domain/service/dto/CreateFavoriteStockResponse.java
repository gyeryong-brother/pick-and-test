package com.gyeryongbrother.pickandtest.domain.service.dto;

public record CreateFavoriteStockResponse(
        Long id,
        Long memberId,
        Long stockId
) {
}
