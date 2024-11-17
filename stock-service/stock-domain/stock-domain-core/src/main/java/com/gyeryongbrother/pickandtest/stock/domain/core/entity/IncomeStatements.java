package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.AnnualIncomeStatement;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IncomeStatements {

    private final List<IncomeStatement> values;

    public static IncomeStatements from(List<IncomeStatement> incomeStatements) {
        return new IncomeStatements(incomeStatements);
    }

    public List<AnnualIncomeStatement> getAnnualIncomeStatements() {
        Map<Integer, AnnualIncomeStatement> annualIncomeStatementByYear = values.stream()
                .collect(toMap(IncomeStatement::getYear, AnnualIncomeStatement::from, AnnualIncomeStatement::add));
        return annualIncomeStatementByYear.values().stream()
                .sorted(comparing(AnnualIncomeStatement::getYear))
                .toList();
    }
}
