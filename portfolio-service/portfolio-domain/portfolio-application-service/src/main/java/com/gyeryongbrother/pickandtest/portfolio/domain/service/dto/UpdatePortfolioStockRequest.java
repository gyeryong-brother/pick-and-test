package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import java.math.BigDecimal;

public record UpdatePortfolioStockRequest(
        Long stockId,
        BigDecimal portion
) {

    UpdatePortfolioStockCommand toCommand() {
        return new UpdatePortfolioStockCommand(stockId, portion);
    }
}
