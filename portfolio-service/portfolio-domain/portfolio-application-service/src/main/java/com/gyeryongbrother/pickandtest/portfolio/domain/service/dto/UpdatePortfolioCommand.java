package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStocks;
import java.util.List;

public record UpdatePortfolioCommand(
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands,
        Long portfolioId
) {

    PortfolioStocks toDomain(Long portfolioId){
        List<PortfolioStock> portfolioStocks=updatePortfolioStockCommands.stream()
                .map(it->it.toDomain(portfolioId))
                .toList();
        return new PortfolioStocks(portfolioStocks,portfolioId);
    }
}
