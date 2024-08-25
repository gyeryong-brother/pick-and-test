package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.application.dto.UpdatePortfolioStockRequests;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import java.util.List;

public interface PortfolioService {

    List<PortfolioStock> updatePortfolioStocks(Long portfolioId , UpdatePortfolioStockRequests updatePortfolioStockRequests);
}
