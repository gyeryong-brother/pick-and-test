package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

import java.time.LocalDate;

public class IncomeStatementEntityFixture {

    public static IncomeStatementEntity incomeStatementEntity(
            StockEntity stockEntity,
            Long operatingIncome,
            Long revenue,
            Long netIncome,
            LocalDate date
    ) {
        return IncomeStatementEntity.builder()
                .stock(stockEntity)
                .operatingIncome(operatingIncome)
                .revenue(revenue)
                .netIncome(netIncome)
                .date(date)
                .build();
    }
}
