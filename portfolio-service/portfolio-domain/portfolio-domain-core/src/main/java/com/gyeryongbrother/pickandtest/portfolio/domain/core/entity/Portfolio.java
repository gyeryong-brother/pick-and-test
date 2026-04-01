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
    private final Long virtualInvestment;
    private final List<PortfolioStock> portfolioStocks;

    public List<PortfolioStock> getPortfolioStocks() {
        if (portfolioStocks == null) {
            return List.of();
        }
        return portfolioStocks;
    }

    public Portfolio deductInvestment(Long amount) {
        if (this.virtualInvestment == null || this.virtualInvestment < amount) {
            throw new IllegalArgumentException("Insufficient virtual investment");
        }
        return Portfolio.builder()
                .id(this.id)
                .memberId(this.memberId)
                .virtualInvestment(this.virtualInvestment - amount)
                .portfolioStocks(this.portfolioStocks)
                .build();
    }
}
