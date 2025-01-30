package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import java.util.List;

public interface PortfolioQueryRepository {

    List<Portfolio> findAllByMemberId(Long memberId);

    Portfolio findById(Long portfolioId);
}
