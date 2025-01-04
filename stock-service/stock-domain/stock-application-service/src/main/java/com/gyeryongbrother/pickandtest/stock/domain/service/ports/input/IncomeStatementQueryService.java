package com.gyeryongbrother.pickandtest.stock.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualIncomeStatementResponse;
import java.util.List;

public interface IncomeStatementQueryService {

    List<AnnualIncomeStatementResponse> getAnnualIncomeStatementsById(Long id);
}
