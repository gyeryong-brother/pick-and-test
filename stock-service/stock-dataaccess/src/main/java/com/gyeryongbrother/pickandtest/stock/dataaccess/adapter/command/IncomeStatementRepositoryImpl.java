package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.command;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.IncomeStatementEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.IncomeStatementDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.IncomeStatementJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IncomeStatementRepositoryImpl implements IncomeStatementRepository {

    private final IncomeStatementJpaRepository incomeStatementJpaRepository;
    private final IncomeStatementDataAccessMapper incomeStatementDataAccessMapper;

    @Override
    public IncomeStatement save(IncomeStatement incomeStatement) {
        IncomeStatementEntity incomeStatementEntity =
                incomeStatementDataAccessMapper.incomeStatementToIncomeStatementEntity(incomeStatement);
        IncomeStatementEntity savedIncomeStatementEntity = incomeStatementJpaRepository.save(incomeStatementEntity);
        return incomeStatementDataAccessMapper.incomeStatementEntityToIncomeStatement(savedIncomeStatementEntity);
    }
}
