package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioStockResponse;
import java.util.List;

public interface PortfolioStockQueryService {

    List<PortfolioStockResponse> findAllByPortfolioId(Long portfolioId);
}
