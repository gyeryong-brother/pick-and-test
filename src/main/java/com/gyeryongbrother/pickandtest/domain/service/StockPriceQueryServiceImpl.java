package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockPriceQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockPriceQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockPriceQueryServiceImpl implements StockPriceQueryService {

    private final StockPriceQueryRepository stockPriceQueryRepository;

    @Override
    public List<StockPriceResponse> findAllByStockId(Long stockId) {
        return stockPriceQueryRepository.findAllByStockId(stockId);
    }
}
