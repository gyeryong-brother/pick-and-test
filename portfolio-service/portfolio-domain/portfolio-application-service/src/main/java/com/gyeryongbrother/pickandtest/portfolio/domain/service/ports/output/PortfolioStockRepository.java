package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;

public interface PortfolioStockRepository {

    PortfolioStock save(PortfolioStock portfolioStock);

    void deleteAllByPortfolioId(Long portfolioId);

    List<PortfolioStock> saveAll(List<PortfolioStock> portfolioStocks);
}
