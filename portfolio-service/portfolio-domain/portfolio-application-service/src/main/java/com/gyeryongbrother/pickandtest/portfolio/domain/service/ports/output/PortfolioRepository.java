package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;

public interface PortfolioRepository {

    Portfolio save(Portfolio portfolio);

    void deleteById(Long Id);
}
