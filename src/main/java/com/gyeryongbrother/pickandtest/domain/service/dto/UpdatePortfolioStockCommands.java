package com.gyeryongbrother.pickandtest.domain.service.dto;

import java.util.List;

public record UpdatePortfolioStockCommands(
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands,
        Long portfolioId
) {
}
