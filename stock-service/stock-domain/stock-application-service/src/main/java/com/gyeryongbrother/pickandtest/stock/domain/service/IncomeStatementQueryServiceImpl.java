package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatements;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.IncomeStatementQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.AnnualIncomeStatement;
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
