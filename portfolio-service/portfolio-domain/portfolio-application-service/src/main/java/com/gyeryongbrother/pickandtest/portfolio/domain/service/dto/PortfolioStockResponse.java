package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.math.BigDecimal;

public record PortfolioStockResponse(
        Long stockId,
        BigDecimal portion
) {

    public static PortfolioStockResponse from(PortfolioStock portfolioStock) {
        return new PortfolioStockResponse(
                portfolioStock.getStockId(),
                portfolioStock.getPortion()
        );
    }
}
