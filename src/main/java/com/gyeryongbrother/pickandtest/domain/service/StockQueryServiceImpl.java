package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockQueryRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockQueryServiceImpl implements StockQueryService {

    private final StockQueryRepository stockQueryRepository;
    private final FavoriteStockQueryRepository favoriteStockQueryRepository;

    @Override
    public List<StockResponse> findAllByNameOrSymbol(String keyword) {
        List<Stock> stocks = stockQueryRepository.findAllByNameOrSymbol(keyword);
        return stocks.stream()
                .map(StockResponse::from)
                .toList();
    }

    @Override
    public List<FavoriteStockResponse> findAllFavoriteStocksByMemberId(Long memberId) {
        List<FavoriteStock> favoriteStocks = favoriteStockQueryRepository.findAllByMemberId(memberId);
        return favoriteStocks.stream()
                .map(FavoriteStockResponse::from)
                .toList();
    }
}
