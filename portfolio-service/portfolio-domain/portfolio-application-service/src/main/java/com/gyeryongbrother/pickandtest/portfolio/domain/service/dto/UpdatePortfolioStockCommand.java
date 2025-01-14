package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.math.BigDecimal;

public record UpdatePortfolioStockCommand(
        Long stockId,
        BigDecimal portion
) {

    PortfolioStock toDomain(Long portfolioId){
        return PortfolioStock.builder()
                .stockId(stockId)
                .portion(portion)
                .portfolioId(portfolioId)
                .build();
    }
}
