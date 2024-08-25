package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import java.util.List;

public interface PortfolioStockRepository {

    PortfolioStock save(PortfolioStock portfolioStock);

    void deleteAllByPortfolioId(Long portfolioId);


}
