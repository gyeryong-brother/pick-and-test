package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Portfolio;

public interface PortfolioRepository {

    Portfolio save(Portfolio portfolio);
}
