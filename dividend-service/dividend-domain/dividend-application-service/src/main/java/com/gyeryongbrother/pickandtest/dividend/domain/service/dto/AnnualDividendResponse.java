package com.gyeryongbrother.pickandtest.dividend.domain.service.dto;

import com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject.AnnualDividend;
import java.math.BigDecimal;

public record AnnualDividendResponse(
        Integer year,
        BigDecimal amount
) {

    public static AnnualDividendResponse from(AnnualDividend annualDividend) {
        return new AnnualDividendResponse(
                annualDividend.year(),
                annualDividend.amount()
        );
    }
}
