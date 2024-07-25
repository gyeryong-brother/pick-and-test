package com.gyeryongbrother.pickandtest.domain.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StockPriceResponse(
        LocalDate date,
        BigDecimal price
) {
}
