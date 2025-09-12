package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceQueryService;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockPriceQueryServiceImpl implements StockPriceQueryService {

    private final StockPriceQueryRepository stockPriceQueryRepository;

    @Override
    @Cacheable(value = "stock-prices", key = "#stockId")
    public List<StockPriceResponse> findAllByStockId(Long stockId) {
        List<StockPrice> stockPrices = stockPriceQueryRepository.findAllByStockId(stockId);
        return stockPrices.stream()
                .map(StockPriceResponse::from)
                .toList();
    }
}
