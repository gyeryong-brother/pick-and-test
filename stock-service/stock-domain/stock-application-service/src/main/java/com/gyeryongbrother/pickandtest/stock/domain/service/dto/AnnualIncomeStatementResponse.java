package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.AnnualIncomeStatement;

public record AnnualIncomeStatementResponse(
        Integer year,
        Long operatingIncome,
        Long revenue,
        Long netIncome
) {

    public static AnnualIncomeStatementResponse from(AnnualIncomeStatement annualIncomeStatement) {
        return new AnnualIncomeStatementResponse(
                annualIncomeStatement.getYear(),
                annualIncomeStatement.getOperatingIncome(),
                annualIncomeStatement.getRevenue(),
                annualIncomeStatement.getNetIncome()
        );
    }
}
