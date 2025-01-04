package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;

public interface IncomeStatementRepository {

    IncomeStatement save(IncomeStatement incomeStatement);
}
