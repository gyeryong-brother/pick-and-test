package com.gyeryongbrother.pickandtest.domain.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class AnnualDividend {

    private final Integer year;
    private final BigDecimal amount;

    public static AnnualDividend from(Dividend dividend) {
        return new AnnualDividend(dividend.getYear(), dividend.getAmount());
    }

    public AnnualDividend add(AnnualDividend other) {
        return new AnnualDividend(year, amount.add(other.amount));
    }
}
