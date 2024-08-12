package com.gyeryongbrother.pickandtest.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture.apple;

import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import java.time.LocalDate;

public class IncomeStatementFixture {

    public static IncomeStatement appleIncomeStatement() {
        return IncomeStatement.builder()
                .stock(apple())
                .revenue(100L)
                .operatingIncome(200L)
                .netIncome(50L)
                .date(LocalDate.of(2024, 8, 8))
                .build();
    }

    public static IncomeStatement incomeStatement(
            Stock stock,
            Long operatingIncome,
            Long revenue,
            Long netIncome,
            LocalDate date
    ) {
        return IncomeStatement.builder()
                .stock(stock)
                .operatingIncome(operatingIncome)
                .revenue(revenue)
                .netIncome(netIncome)
                .date(date)
                .build();
    }
}
