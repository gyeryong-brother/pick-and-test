package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.AnnualIncomeStatement;

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
