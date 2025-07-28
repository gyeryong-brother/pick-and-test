package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import java.util.List;

public record PortfolioRequest(
        List<UpdatePortfolioStockRequest> updatePortfolioStockRequests
) {

    public UpdatePortfolioCommand UpdatePortfoliotoCommand(Long memberId,Long portfolioId) {
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands = updatePortfolioStockRequests.stream()
                .map(UpdatePortfolioStockRequest::toCommand)
                .toList();
        return new UpdatePortfolioCommand(memberId,updatePortfolioStockCommands,portfolioId);
    }

    public SavePortfolioCommand SavePortfoliotoCommand(Long memberId) {
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands = updatePortfolioStockRequests.stream()
                .map(UpdatePortfolioStockRequest::toCommand)
                .toList();
        return new SavePortfolioCommand(memberId,updatePortfolioStockCommands);
    }
}
