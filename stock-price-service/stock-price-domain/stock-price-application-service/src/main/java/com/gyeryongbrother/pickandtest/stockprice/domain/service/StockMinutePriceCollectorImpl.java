package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockMinutePriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockMinutePriceQueryRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockMinutePriceRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockMinutePriceCollectorImpl implements StockMinutePriceCollector {

    private final StockMinutePriceRepository stockMinutePriceRepository;
    private final StockMinutePriceQueryRepository stockMinutePriceQueryRepository;
    private final StockPriceFetcher stockPriceFetcher;

    @Override
    public void collectStockMinutePrices(Long stockId) {
        LocalDate lastDate = stockMinutePriceQueryRepository.findLastDateOfStockMinutePricesByStockId(stockId);
        List<StockMinutePrice> stockMinutePrices = fetchStockMinutePrices(stockId, lastDate);
        log.info("save stock minute prices. size: {}", stockMinutePrices.size());
        stockMinutePriceRepository.saveAll(stockMinutePrices);
    }

    private List<StockMinutePrice> fetchStockMinutePrices(Long stockId, LocalDate lastDate) {
        if (lastDate == null) {
            return stockPriceFetcher.fetchStockMinutePrices(stockId, null);
        }
        return stockPriceFetcher.fetchStockMinutePrices(stockId, lastDate.plusDays(1));
    }
}
