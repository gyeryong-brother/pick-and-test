package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.IncomeStatementDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.IncomeStatementJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.IncomeStatementRepository;
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
