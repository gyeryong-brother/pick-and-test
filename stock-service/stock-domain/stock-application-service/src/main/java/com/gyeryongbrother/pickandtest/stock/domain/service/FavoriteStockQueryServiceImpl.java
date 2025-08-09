package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.FavoriteStockQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteStockQueryServiceImpl implements FavoriteStockQueryService {

    private final FavoriteStockQueryRepository favoriteStockQueryRepository;

    @Override
    public List<FavoriteStockResponse> findAllFavoriteStocksByMemberId(Long memberId) {
        List<FavoriteStock> favoriteStocks = favoriteStockQueryRepository.findAllByMemberId(memberId);
        return favoriteStocks.stream()
                .map(FavoriteStockResponse::from)
                .toList();
    }

    @Override
    public FavoriteStockResponse findFavoriteStockByStockIdAndMemberId(Long stockId, Long memberId) {
        FavoriteStock favoriteStock = favoriteStockQueryRepository.getByStockIdAndMemberId(stockId, memberId);
        return FavoriteStockResponse.from(favoriteStock);
    }
}
