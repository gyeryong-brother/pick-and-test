package com.gyeryongbrother.pickandtest.domain.core;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class PortfolioStock {

    private final Long id;
    private final Long portfolioId;
    private final Long stockId;
    private final BigDecimal portion;
}
