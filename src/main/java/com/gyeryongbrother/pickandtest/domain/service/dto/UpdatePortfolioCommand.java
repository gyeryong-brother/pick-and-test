package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.application.dto.UpdatePortfolioStockRequests;

public record UpdatePortfolioCommand(
        Long portfolioId,
        UpdatePortfolioStockRequests updatePortfolioStockRequests
) {
}
