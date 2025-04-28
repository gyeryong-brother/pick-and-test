package com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import java.math.BigDecimal;

public class AnnualDividend {

    private final Integer year;
    private final BigDecimal amount;

    public AnnualDividend(Integer year, BigDecimal amount) {
        this.year = year;
        this.amount = amount;
    }

    public static AnnualDividend from(Dividend dividend) {
        return new AnnualDividend(dividend.year(), dividend.amount());
    }

    public AnnualDividend add(AnnualDividend other) {
        return new AnnualDividend(year, amount.add(other.amount));
    }

    public Integer year() {
        return year;
    }

    public BigDecimal amount() {
        return amount;
    }
}
