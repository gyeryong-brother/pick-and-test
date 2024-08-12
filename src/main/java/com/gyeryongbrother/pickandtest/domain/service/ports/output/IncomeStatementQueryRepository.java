package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import java.util.List;

public interface IncomeStatementQueryRepository {

    List<IncomeStatement> findAllByStockId(Long stockId);
}
