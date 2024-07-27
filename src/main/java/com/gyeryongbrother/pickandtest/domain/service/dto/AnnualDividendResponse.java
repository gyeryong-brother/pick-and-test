package com.gyeryongbrother.pickandtest.domain.service.dto;

import java.math.BigDecimal;

public record AnnualDividendResponse(
        Integer year,
        BigDecimal amount
) {
}
