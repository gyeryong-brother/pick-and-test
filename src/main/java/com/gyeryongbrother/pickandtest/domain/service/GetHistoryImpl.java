package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.GetHistory;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.AnnualDividend;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.AnnualDividendMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHistoryImpl implements GetHistory {

    public final StockQueryRepository stockQueryRepository;
    public final AnnualDividendMapper annualDividendMapper;

    @Override
    public List<AnnualDividend> getAnnualDividendHistoryByName(String name) {
        Stock stock = stockQueryRepository.findByName(name);
        List<Dividend> dividends = stock.getDividends();
        return annualDividendMapper.DividendsToAnnualDividends(dividends);
    }
}
