package com.gyeryongbrother.pickandtest.domain.core;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Portfolio {

    private final Long id;
    private final List<PortfolioStock> portfolioStocks;
}
