package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockPriceQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockPriceQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockPriceQueryServiceImpl implements StockPriceQueryService {

    private final StockPriceQueryRepository stockPriceQueryRepository;

    @Override
    public List<StockPriceResponse> findAllByStockId(Long stockId) {
        List<StockPrice> stockPrices = stockPriceQueryRepository.findAllByStockId(stockId);
        return stockPrices.stream()
                .map(StockPriceResponse::from)
                .toList();
    }
}
