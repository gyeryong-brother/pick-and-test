package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import java.util.List;

public record UpdatePortfolioRequest(
        List<UpdatePortfolioStockRequest> updatePortfolioStockRequests
) {

    UpdatePortfolioCommand toCommand(Long portfolioId){
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands=updatePortfolioStockRequests.stream()
                .map(UpdatePortfolioStockRequest::toCommand)
                .toList();
        return new UpdatePortfolioCommand(updatePortfolioStockCommands,portfolioId);
    }
}
