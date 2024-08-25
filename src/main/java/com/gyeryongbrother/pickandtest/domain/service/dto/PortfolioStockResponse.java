package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import java.math.BigDecimal;

public record PortfolioStockResponse(
        Long stockId,
        BigDecimal portion
) {

    public static PortfolioStockResponse from(PortfolioStock portfolioStock) {
        return new PortfolioStockResponse(
                portfolioStock.getStock().getId(),
                portfolioStock.getPortion()
        );
    }
}
