package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import java.util.List;

public interface PortfolioQueryRepository {

    List<Portfolio> findAllByMemberId(Long memberId);
}
