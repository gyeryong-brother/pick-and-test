package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import java.util.List;

public interface PortfolioService {

    UpdatePortfolioResponse updatePortfolio(UpdatePortfolioCommand updatePortfolioCommand);
}
