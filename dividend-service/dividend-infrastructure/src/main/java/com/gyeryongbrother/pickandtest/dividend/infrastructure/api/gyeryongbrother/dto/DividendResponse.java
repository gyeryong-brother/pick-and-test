package com.gyeryongbrother.pickandtest.dividend.infrastructure.api.gyeryongbrother.dto;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record DividendResponse(
        String date,
        String amount
) {

    public Dividend toDomain(Long stockId) {
        return new Dividend(null, stockId, toDate(), toAmount());
    }

    private LocalDate toDate() {
        return OffsetDateTime.parse(date)
                .toLocalDate();
    }

    private BigDecimal toAmount() {
        return new BigDecimal(amount);
    }
}
