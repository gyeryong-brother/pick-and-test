package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.AnnualDividend;
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
