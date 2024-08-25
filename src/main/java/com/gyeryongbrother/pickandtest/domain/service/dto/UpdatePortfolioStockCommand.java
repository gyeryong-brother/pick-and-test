package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import java.math.BigDecimal;

public record UpdatePortfolioStockCommand(
        Long portfolioId,
        Long stockId,
        BigDecimal portion
) {
    public PortfolioStock toDomain() {
        Stock stock = Stock.builder()
                .id(stockId)
                .build();
        return PortfolioStock.builder()
                .portfolioId(portfolioId)
                .stock(stock)
                .portion(portion)
                .build();
    }
}
