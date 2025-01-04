package com.gyeryongbrother.pickandtest.stock.domain.core.valueobject;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
