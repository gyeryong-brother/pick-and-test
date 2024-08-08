package com.gyeryongbrother.pickandtest.domain.core;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class IncomeStatement {

    private final Long id;
    private final Stock stock;
    private final LocalDate date;
    private final Long operatingIncome;
    private final Long revenue;
    private final Long netIncome;
}
