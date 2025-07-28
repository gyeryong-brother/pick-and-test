package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

public record FindPortfolioStocksRequest(
        Long memberId,
        Long portfolioId
) {
}
