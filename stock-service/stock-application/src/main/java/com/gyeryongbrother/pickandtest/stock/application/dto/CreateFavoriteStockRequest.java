package com.gyeryongbrother.pickandtest.stock.application.dto;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;

public record CreateFavoriteStockRequest(
        Long stockId,
        Long memberId
) {

    public CreateFavoriteStockCommand toCommand() {
        return new CreateFavoriteStockCommand(stockId, memberId);
    }
}
