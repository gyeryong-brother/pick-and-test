package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.math.BigDecimal;

public record UpdatePortfolioStockResponse(
        Long stockId,
        BigDecimal portion
) {

    public static UpdatePortfolioStockResponse from(PortfolioStock portfolioStock){
        return new UpdatePortfolioStockResponse(
                portfolioStock.getStockId()
                ,portfolioStock.getPortion()
        );
    }
}
