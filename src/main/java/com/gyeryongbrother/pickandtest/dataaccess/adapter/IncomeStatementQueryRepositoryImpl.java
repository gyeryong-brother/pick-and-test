package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QIncomeStatementEntity.incomeStatementEntity;

import com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.QIncomeStatementEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.IncomeStatementDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.IncomeStatementJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.IncomeStatementQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncomeStatementQueryRepositoryImpl implements IncomeStatementQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final StockDataAccessMapper stockDataAccessMapper;
    private final IncomeStatementDataAccessMapper incomeStatementDataAccessMapper;

    @Override
    public List<IncomeStatement> findAllByStockId(Long stockId){
        List<IncomeStatementEntity> incomeStatementEntities= queryFactory.selectFrom(incomeStatementEntity)
                .where(incomeStatementEntity.stock.id.eq(stockId))
                .fetch();
        return incomeStatementEntities.stream()
                .map(incomeStatementDataAccessMapper::incomeStatementEntityToIncomeStatement)
                .toList();
    }
}
