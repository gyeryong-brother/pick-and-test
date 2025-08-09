package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.apple;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.twentyTwenty;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import java.time.LocalDate;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
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

    public static List<IncomeStatement> incomeStatements(Stock stock) {
        return List.of(
                incomeStatement(stock, 200L, 100L, 50L, januaryFirst()),
                incomeStatement(stock, 220L, 100L, 50L, januarySecond()),
                incomeStatement(stock, 220L, 100L, 50L, twentyTwenty())
        );
    }
}
