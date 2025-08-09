package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import java.util.List;

public interface IncomeStatementQueryRepository {

    List<IncomeStatement> findAllByStockId(Long stockId);
}
