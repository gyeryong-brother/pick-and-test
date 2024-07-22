package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.AnnualDividend;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class AnnualDividendMapper {

    public List<AnnualDividend> DividendsToAnnualDividends(List<Dividend> dividends) {
        Map<Integer, BigDecimal> dividendHistory = new HashMap<Integer, BigDecimal>();
        for (Dividend dividend : dividends) {
            int year = dividend.getDate().getYear();
            BigDecimal amount = dividend.getAmount();
            dividendHistory.merge(year, amount, BigDecimal::add);
        }
        List<AnnualDividend> annualDividends = new ArrayList<>();
        for (Map.Entry<Integer, BigDecimal> entry : dividendHistory.entrySet()) {
            annualDividends.add(new AnnualDividend(entry.getKey(), entry.getValue()));
        }
        return annualDividends;
    }
}
