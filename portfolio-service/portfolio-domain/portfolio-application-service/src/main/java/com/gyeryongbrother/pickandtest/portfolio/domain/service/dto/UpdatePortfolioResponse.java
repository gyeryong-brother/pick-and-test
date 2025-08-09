package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import java.util.List;

public record UpdatePortfolioResponse(
        List<UpdatePortfolioStockResponse> updatePortfolioStockResponses,
        Long portfolioId
) {

    public static UpdatePortfolioResponse from(Portfolio portfolio) {
        List<UpdatePortfolioStockResponse> updatePortfolioStockResponses = portfolio.getPortfolioStocks().stream()
                .map(UpdatePortfolioStockResponse::from)
                .toList();
        return new UpdatePortfolioResponse(
                updatePortfolioStockResponses,
                portfolio.getId()
        );
    }
}
