package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.scheduler.StockPriceScheduler;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockPriceSchedulerImpl implements StockPriceScheduler {

    private final StockPriceQueryRepository stockPriceQueryRepository;
    private final StockPriceCollector stockPriceCollector;

    @Override
    @Scheduled(cron = "0 0 12 * * ?")
    public void saveStockPrices() {
        List<Long> stockIds = stockPriceQueryRepository.findAllStockIds();
        stockIds.forEach(stockPriceCollector::collectStockPrices);
    }
}
