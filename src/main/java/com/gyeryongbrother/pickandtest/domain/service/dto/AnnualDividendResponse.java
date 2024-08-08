package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.AnnualDividend;
import java.math.BigDecimal;

public record AnnualDividendResponse(
        Integer year,
        BigDecimal amount
) {

    public static AnnualDividendResponse from(AnnualDividend annualDividend) {
        return new AnnualDividendResponse(
                annualDividend.getYear(),
                annualDividend.getAmount()
        );
    }
}
