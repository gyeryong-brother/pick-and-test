package com.gyeryongbrother.pickandtest.stock.application.dto;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;

public record CreateFavoriteStockRequest(
        Long memberId
) {

    public CreateFavoriteStockCommand toCommand(Long stockId) {
        return new CreateFavoriteStockCommand(memberId, stockId);
    }
}
