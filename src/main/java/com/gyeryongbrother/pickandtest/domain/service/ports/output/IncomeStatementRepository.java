package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;

public interface IncomeStatementRepository {

    IncomeStatement save(IncomeStatement incomeStatement);
}
