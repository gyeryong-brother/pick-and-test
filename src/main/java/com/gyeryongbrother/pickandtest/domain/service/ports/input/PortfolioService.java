package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioCommand;
import java.util.List;

public interface PortfolioService {

    List<PortfolioStock> updatePortfolioStocks(UpdatePortfolioCommand updatePortfolioCommand);
}
