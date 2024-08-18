package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import java.util.List;

public interface PortfolioStockQueryRepository {

    List<PortfolioStock> findAllByPortfolioId(Long portfolioId);
}
