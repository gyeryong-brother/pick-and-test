package com.gyeryongbrother.pickandtest.portfolio.domain.core.entity;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class PortfolioStocks {

    private final List<PortfolioStock> portfolioStocks;
    private final Long portfolioId;
}
