package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto;

import java.math.BigDecimal;

public record AnnualDividend(
        Integer year,
        BigDecimal amount
) {
}
