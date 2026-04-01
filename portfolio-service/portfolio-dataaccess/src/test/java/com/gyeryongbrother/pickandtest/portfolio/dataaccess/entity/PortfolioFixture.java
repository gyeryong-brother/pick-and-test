package com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;

public class PortfolioFixture {

    public static Portfolio portfolio1() {
        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStockFixture.apple(),
                PortfolioStockFixture.microsoft()
        );
        return new Portfolio(null, 1L, null, portfolioStocks);
    }

    public static Portfolio portfolio2() {
        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStockFixture.nvidia(),
                PortfolioStockFixture.tesla()
        );
        return new Portfolio(null, 2L, null, portfolioStocks);
    }

    public static Portfolio portfolio3() {
        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStockFixture.google(),
                PortfolioStockFixture.meta(),
                PortfolioStockFixture.amazon()
        );
        return new Portfolio(null, 1L, null, portfolioStocks);
    }

    public static Portfolio portfolio1Update() {
        List<PortfolioStock> portfolioStocks = List.of(
                PortfolioStockFixture.apple(),
                PortfolioStockFixture.tesla()
        );
        return new Portfolio(null, 3L, null, portfolioStocks);
    }
}
