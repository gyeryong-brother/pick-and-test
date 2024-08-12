package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntity;
import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncomeStatementDataAccessMapper {

    private final StockDataAccessMapper stockDataAccessMapper;

    public IncomeStatementEntity incomeStatementToIncomeStatementEntity(IncomeStatement incomeStatement) {
        return IncomeStatementEntity.builder()
                .id(incomeStatement.getId())
                .stock(stockDataAccessMapper.stockToStockEntity(incomeStatement.getStock()))
                .date(incomeStatement.getDate())
                .operatingIncome(incomeStatement.getOperatingIncome())
                .revenue(incomeStatement.getRevenue())
                .netIncome(incomeStatement.getNetIncome())
                .build();
    }

    public IncomeStatement incomeStatementEntityToIncomeStatement(IncomeStatementEntity incomeStatementEntity) {
        return IncomeStatement.builder()
                .id(incomeStatementEntity.getId())
                .stock(stockDataAccessMapper.stockEntityToStock(incomeStatementEntity.getStock()))
                .date(incomeStatementEntity.getDate())
                .operatingIncome(incomeStatementEntity.getOperatingIncome())
                .revenue(incomeStatementEntity.getRevenue())
                .netIncome(incomeStatementEntity.getNetIncome())
                .build();
    }
}
