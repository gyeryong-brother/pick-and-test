package com.gyeryongbrother.pickandtest.domain.core;

import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Dividends {

    private final List<Dividend> values;

    public List<AnnualDividendResponse> getAnnualDividends() {
        Map<Integer, BigDecimal> dividendHistory = new HashMap<>();
        for (Dividend dividend : values) {
            int year = dividend.getDate().getYear();
            BigDecimal amount = dividend.getAmount();
            dividendHistory.merge(year, amount, BigDecimal::add);
        }
        List<AnnualDividendResponse> annualDividends = new ArrayList<>();
        for (Map.Entry<Integer, BigDecimal> entry : dividendHistory.entrySet()) {
            annualDividends.add(new AnnualDividendResponse(entry.getKey(), entry.getValue()));
        }
        return annualDividends;
    }
}
