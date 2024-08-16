package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.AnnualIncomeStatement;
import com.gyeryongbrother.pickandtest.domain.core.IncomeStatements;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.IncomeStatementQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.IncomeStatementQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IncomeStatementQueryServiceImpl implements IncomeStatementQueryService {

    private final IncomeStatementQueryRepository incomeStatementQueryRepository;

    @Override
    public List<AnnualIncomeStatementResponse> getAnnualIncomeStatementsById(Long id) {
        IncomeStatements incomeStatements = IncomeStatements.from(incomeStatementQueryRepository.findAllByStockId(id));
        List<AnnualIncomeStatement> annualIncomeStatements = incomeStatements.getAnnualIncomeStatements();
        return annualIncomeStatements.stream()
                .map(AnnualIncomeStatementResponse::from)
                .toList();
    }
}
