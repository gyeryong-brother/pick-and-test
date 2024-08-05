package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockQueryServiceImpl implements StockQueryService {

    private final StockQueryRepository stockQueryRepository;

    @Override
    public List<StockResponse> findAllByNameOrSymbol(String keyword) {
        List<Stock> stocks = stockQueryRepository.findAllByNameOrSymbol(keyword);
        return stocks.stream()
                .map(StockResponse::from)
                .toList();
    }
}
