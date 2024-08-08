package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import com.gyeryongbrother.pickandtest.domain.core.Stock;

import java.util.List;

public interface IncomeStatementQueryRepository {

    List<IncomeStatement> findAllByStock(Stock stock);
}
