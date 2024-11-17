package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;

public record PortfolioResponse(
        Long id
) {

    public static PortfolioResponse from(Portfolio portfolio) {
        return new PortfolioResponse(portfolio.getId());
    }
}
