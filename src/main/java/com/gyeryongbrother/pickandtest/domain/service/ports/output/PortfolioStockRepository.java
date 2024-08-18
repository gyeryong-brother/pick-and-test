package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;

public interface PortfolioStockRepository {

    PortfolioStock save(PortfolioStock portfolioStock);
}
