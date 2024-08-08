package com.gyeryongbrother.pickandtest.dataaccess.entity;

import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture;

import java.time.LocalDate;
import java.util.List;

public class IncomeStatementFixture {

    public static IncomeStatement appleIncomeStatement(){
        return IncomeStatement.builder()
                .stock(StockFixture.apple())
                .revenue(100L)
                .operatingIncome(200L)
                .netIncome(50L)
                .date(LocalDate.of(2024,8,8))
                .build();
    }

    public static List<IncomeStatement> appleIncomeStatments(){
        return List.of(
                incomeStatement(StockFixture.apple(),200L,100L,50L,LocalDateFixture.januaryFirst()),
                incomeStatement(StockFixture.apple(),220L,110L,50L,LocalDateFixture.januarySecond()),
                incomeStatement(StockFixture.apple(),250L,130L,70L,LocalDateFixture.januaryThird())
        );
    }

    public static List<IncomeStatement> nvidiaIncomeStatemetnts(){
        return List.of(
                incomeStatement(StockFixture.nvidia(),200L,100L,50L,LocalDateFixture.januaryFirst()),
                incomeStatement(StockFixture.nvidia(),300L,150L,100L,LocalDateFixture.januarySecond()),
                incomeStatement(StockFixture.nvidia(),300L,150L,100L,LocalDateFixture.januaryThird())
        );
    }

    public static IncomeStatement incomeStatement(
            Stock stock,
            Long operatingIncome,
            Long revenue,
            Long netIncome,
            LocalDate date
    ){
        return IncomeStatement.builder()
                .stock(stock)
                .operatingIncome(operatingIncome)
                .revenue(revenue)
                .netIncome(netIncome)
                .date(date)
                .build();
    }
}
