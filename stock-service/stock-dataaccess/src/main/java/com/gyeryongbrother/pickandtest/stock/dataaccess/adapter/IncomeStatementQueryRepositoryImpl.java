package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QIncomeStatementEntity.incomeStatementEntity;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.IncomeStatementEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.IncomeStatementDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IncomeStatementQueryRepositoryImpl implements IncomeStatementQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final IncomeStatementDataAccessMapper incomeStatementDataAccessMapper;

    @Override
    public List<IncomeStatement> findAllByStockId(Long stockId) {
        List<IncomeStatementEntity> incomeStatementEntities = queryFactory.selectFrom(incomeStatementEntity)
                .where(incomeStatementEntity.stock.id.eq(stockId))
                .fetch();
        return incomeStatementEntities.stream()
                .map(incomeStatementDataAccessMapper::incomeStatementEntityToIncomeStatement)
                .toList();
    }
}
