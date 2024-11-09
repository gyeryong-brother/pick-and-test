package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import java.util.List;

public record PortfolioResponse(
        Long id,
        List<PortfolioStock> portfolioStocks
) {

    public static PortfolioResponse from(Portfolio portfolio) {
        List<PortfolioStock> portfolioStocks = portfolio.getPortfolioStocks();
        return new PortfolioResponse(portfolio.getId(), portfolioStocks);
    }
}
