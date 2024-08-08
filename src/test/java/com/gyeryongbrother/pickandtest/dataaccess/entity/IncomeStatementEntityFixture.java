package com.gyeryongbrother.pickandtest.dataaccess.entity;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture;

import java.time.LocalDate;
import java.util.List;

public class IncomeStatementEntityFixture {

    public static IncomeStatementEntity appleIncomeStatementEntity(){
        return incomeStatementEntity(StockEntityFixture.stockEntity(),200L,100L,50L,LocalDateFixture.januaryFirst());
    }

    public static List<IncomeStatementEntity> appleIncomeStatementEntities(){
        return List.of(
                incomeStatementEntity(StockEntityFixture.stockEntity(),200L,100L,50L,LocalDateFixture.januaryFirst()),
                incomeStatementEntity(StockEntityFixture.stockEntity(),220L,110L,50L,LocalDateFixture.januarySecond()),
                incomeStatementEntity(StockEntityFixture.stockEntity(),250L,130L,70L,LocalDateFixture.januaryThird())
                );
    }

    public static List<IncomeStatementEntity> nvidiaIncomeStatementEntities(){
        return List.of(
                incomeStatementEntity(StockEntityFixture.nvidiaStockEntity(),200L,100L,50L,LocalDateFixture.januaryFirst()),
                incomeStatementEntity(StockEntityFixture.nvidiaStockEntity(),220L,110L,50L,LocalDateFixture.januarySecond()),
                incomeStatementEntity(StockEntityFixture.nvidiaStockEntity(),250L,130L,70L,LocalDateFixture.januaryThird())
        );
    }

    public static IncomeStatementEntity incomeStatementEntity(
        StockEntity stockEntity,
        Long operatingIncome,
        Long revenue,
        Long netIncome,
        LocalDate date
    ){
        return IncomeStatementEntity.builder()
                .stock(stockEntity)
                .operatingIncome(operatingIncome)
                .revenue(revenue)
                .netIncome(netIncome)
                .date(date)
                .build();
    }
}
