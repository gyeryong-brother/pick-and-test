package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;

public interface PortfolioStockQueryRepository {

    List<PortfolioStock> findAllByPortfolioId(Long portfolioId);
}
