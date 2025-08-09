package com.gyeryongbrother.pickandtest.stock.domain.core.valueobject;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnnualIncomeStatement {

    private final Integer year;
    private final Long operatingIncome;
    private final Long revenue;
    private final Long netIncome;

    public static AnnualIncomeStatement from(IncomeStatement incomeStatement) {
        return new AnnualIncomeStatement(
                incomeStatement.getDate().getYear(),
                incomeStatement.getOperatingIncome(),
                incomeStatement.getRevenue(),
                incomeStatement.getNetIncome()
        );
    }

    public AnnualIncomeStatement add(AnnualIncomeStatement other) {
        return new AnnualIncomeStatement(
                year,
                operatingIncome + other.operatingIncome,
                revenue + other.revenue,
                netIncome + other.netIncome
        );
    }
}
