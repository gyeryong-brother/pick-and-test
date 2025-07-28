package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.DeletePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfoliosResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;

public interface PortfolioService {

    UpdatePortfolioResponse updatePortfolio(UpdatePortfolioCommand updatePortfolioCommand);

    PortfoliosResponse deletePortfolioId(DeletePortfolioCommand deletePortfolioCommand);

    UpdatePortfolioResponse savePortfolio(UpdatePortfolioCommand updatePortfolioCommand);
}
