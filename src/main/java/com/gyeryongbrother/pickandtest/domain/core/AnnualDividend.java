package com.gyeryongbrother.pickandtest.domain.core;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class AnnualDividend {

    public final Integer year;
    public final BigDecimal amount;
}
