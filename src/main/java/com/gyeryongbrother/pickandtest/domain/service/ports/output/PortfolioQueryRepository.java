package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Portfolio;

public interface PortfolioQueryRepository {

    Portfolio findById(Long id);
}
