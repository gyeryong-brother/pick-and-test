package com.gyeryongbrother.pickandtest.domain.core;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public class Dividends {

    private final List<Dividend> values;

    public List<AnnualDividend> getAnnualDividends() {
        Map<Integer, AnnualDividend> annualDividendsByYear = values.stream()
                .collect(toMap(Dividend::getYear, AnnualDividend::from, AnnualDividend::add));
        return annualDividendsByYear.values().stream()
                .sorted(comparing(AnnualDividend::getYear))
                .toList();
    }
}
