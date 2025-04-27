package com.gyeryongbrother.pickandtest.dividend.domain.service;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.DividendCollector;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendFetcher;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendQueryRepository;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DividendCollectorImpl implements DividendCollector {

    private final DividendRepository dividendRepository;
    private final DividendQueryRepository dividendQueryRepository;
    private final DividendFetcher dividendFetcher;

    @Override
    public void collectDividends(Stock stock) {
        LocalDate lastDate = dividendQueryRepository.findLastDateOfDividendsByStockId(stock.id());
        List<Dividend> dividends = fetchDividends(stock, lastDate);
        log.info("save dividends. size: {}", dividends.size());
        dividendRepository.saveAll(dividends);
    }

    private List<Dividend> fetchDividends(Stock stock, LocalDate lastDate) {
        if (lastDate == null) {
            return dividendFetcher.fetchDividends(stock, null);
        }
        return dividendFetcher.fetchDividends(stock, lastDate.plusDays(1));
    }
}
