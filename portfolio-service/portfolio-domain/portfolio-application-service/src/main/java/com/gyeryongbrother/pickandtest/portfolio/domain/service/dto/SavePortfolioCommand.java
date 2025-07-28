package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;

public record SavePortfolioCommand(
        Long memberId,
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands
) {
    public Portfolio toDomain() {
        List<PortfolioStock> portfolioStocks = updatePortfolioStockCommands.stream()
                .map(it -> it.toDomain())
                .toList();
        return Portfolio.builder()
                .memberId(memberId)
                .portfolioStocks(portfolioStocks)
                .build();
    }
}
