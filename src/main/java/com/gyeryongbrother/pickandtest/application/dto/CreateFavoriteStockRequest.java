package com.gyeryongbrother.pickandtest.application.dto;

import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockCommand;

public record CreateFavoriteStockRequest(
        Long memberId
) {

    public CreateFavoriteStockCommand toCommand(Long stockId) {
        return new CreateFavoriteStockCommand(memberId, stockId);
    }
}
