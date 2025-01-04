package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioStockResponse;
import java.util.List;

public interface PortfolioQueryService {

    List<PortfolioStockResponse> findAllByPortfolioId(Long portfolioId);

    List<PortfolioResponse> findAllPortfolios();
}
