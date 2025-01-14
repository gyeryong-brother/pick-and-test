package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;

public interface PortfolioStockRepository {

    PortfolioStock save(PortfolioStock portfolioStock);

    void deleteAllByPortfolioEntity_Id(Long portfolioId);
}
