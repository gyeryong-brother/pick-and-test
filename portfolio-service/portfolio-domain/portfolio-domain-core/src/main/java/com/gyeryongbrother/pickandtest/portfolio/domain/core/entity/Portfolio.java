package com.gyeryongbrother.pickandtest.portfolio.domain.core.entity;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Portfolio {

    private final Long id;
    private final Long memberId;
    private final List<PortfolioStock> portfolioStocks;

    public List<PortfolioStock> getPortfolioStocks() {
        if (portfolioStocks == null) {
            return List.of();
        }
        return portfolioStocks;
    }
}
