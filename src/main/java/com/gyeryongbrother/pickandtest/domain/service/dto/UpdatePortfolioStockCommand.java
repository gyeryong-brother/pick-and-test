package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;

public record UpdatePortfolioStockCommand(
        Long portfolioId,
        Long stockId,
        BigDecimal portion
) {
    public PortfolioStock toDomain(){
        Stock stock=Stock.builder()
                .id(stockId)
                .build();
        return PortfolioStock.builder()
                .portfolioId(portfolioId)
                .stock(stock)
                .portion(portion)
                .build();
    }
}
