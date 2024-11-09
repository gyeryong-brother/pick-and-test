package com.gyeryongbrother.pickandtest.application.dto;

import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioStockCommands;
import java.util.List;

public record UpdatePortfolioStockRequests(
        List<UpdatePortfolioStockRequest> updatePortfolioStockRequests
) {

    public UpdatePortfolioStockCommands toCommand(Long portfolioId) {
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands = updatePortfolioStockRequests.stream()
                .map(updatePortfolioStockRequest -> updatePortfolioStockRequest.toCommand(portfolioId))
                .toList();
        return new UpdatePortfolioStockCommands(updatePortfolioStockCommands, portfolioId);
    }
}
