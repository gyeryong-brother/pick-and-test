package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import java.util.List;

public record PortfoliosResponse(
        List<PortfolioResponse> portfolioResponses
) {
}
