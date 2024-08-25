package com.gyeryongbrother.pickandtest.application.dto;

import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioStockCommand;
import java.math.BigDecimal;

public record UpdatePortfolioStockRequest(
        Long stockId,
        BigDecimal portion
) {
    public UpdatePortfolioStockCommand toCommand(Long portfolioId) {
        return new UpdatePortfolioStockCommand(portfolioId, stockId, portion);
    }
}
