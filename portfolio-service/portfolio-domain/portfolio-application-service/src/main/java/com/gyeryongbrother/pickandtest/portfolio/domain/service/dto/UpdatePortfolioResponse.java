package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStocks;
import java.util.List;

public record UpdatePortfolioResponse(
        List<UpdatePortfolioStockResponse> updatePortfolioStockResponses,
        Long portfolioId
) {

    UpdatePortfolioResponse from(PortfolioStocks portfolioStocks){
        List<UpdatePortfolioStockResponse> updatePortfolioStockResponses=portfolioStocks.getPortfolioStocks().stream()
                .map(UpdatePortfolioStockResponse::from)
                .toList();
        return new UpdatePortfolioResponse(updatePortfolioStockResponses
                ,portfolioStocks.getPortfolioId()
        );
    }
}
