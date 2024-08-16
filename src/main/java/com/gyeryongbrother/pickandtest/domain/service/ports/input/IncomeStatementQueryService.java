package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualIncomeStatementResponse;
import java.util.List;

public interface IncomeStatementQueryService {

    List<AnnualIncomeStatementResponse> getAnnualIncomeStatementsById(Long id);
}
