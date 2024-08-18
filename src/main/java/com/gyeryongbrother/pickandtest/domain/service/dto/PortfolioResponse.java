package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.Portfolio;

public record PortfolioResponse(
        Long id
) {

    public static PortfolioResponse from(Portfolio portfolio){
        return new PortfolioResponse(portfolio.getId());
    }
}
