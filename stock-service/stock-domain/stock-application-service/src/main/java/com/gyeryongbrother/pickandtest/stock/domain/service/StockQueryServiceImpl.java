package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockDetailResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockWithPrices;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.MarketCapitalization;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockQueryServiceImpl implements StockQueryService {

    private final StockQueryRepository stockQueryRepository;
    private final FavoriteStockQueryRepository favoriteStockQueryRepository;

    @Override
    public List<MarketCapitalizationResponse> findAllMarketCapitalizationsByStockId(Long stockId) {
        StockWithPrices stockWithPrices = stockQueryRepository.findStockWithPricesById(stockId);
        List<MarketCapitalization> marketCapitalizations = stockWithPrices.getMarketCapitalizations();
        return marketCapitalizations.stream()
                .map(MarketCapitalizationResponse::from)
                .toList();
    }

    @Override
    public List<StockResponse> findAllByNameOrSymbol(String keyword) {
        List<Stock> stocks = stockQueryRepository.findAllByNameOrSymbol(keyword);
        return stocks.stream()
                .map(StockResponse::from)
                .toList();
    }

    @Override
    public StockDetailResponse findStockById(Long stockId) {
        StockDetail stockDetail = stockQueryRepository.findById(stockId);
        return StockDetailResponse.from(stockDetail);
    }

    @Override
    public List<FavoriteStockResponse> findAllFavoriteStocksByMemberId(Long memberId) {
        List<FavoriteStock> favoriteStocks = favoriteStockQueryRepository.findAllByMemberId(memberId);
        return favoriteStocks.stream()
                .map(FavoriteStockResponse::from)
                .toList();
    }
}
