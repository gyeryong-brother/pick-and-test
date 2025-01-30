package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;

public record UpdatePortfolioCommand(
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands,
        Long portfolioId
) {

    public Portfolio toDomain(Long portfolioId) {
        List<PortfolioStock> portfolioStocks = updatePortfolioStockCommands.stream()
                .map(it -> it.toDomain(portfolioId))
                .toList();
        return Portfolio.builder()
                .portfolioStocks(portfolioStocks)
                .id(portfolioId)
                .build();
    }
}
