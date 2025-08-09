package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

public record DeletePortfolioCommand(
        Long memberId,
        Long portfolioId
) {
}
