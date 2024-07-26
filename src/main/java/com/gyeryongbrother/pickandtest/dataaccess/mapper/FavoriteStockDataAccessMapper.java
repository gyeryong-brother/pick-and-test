package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.FavoriteStockEntity;
import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteStockDataAccessMapper {

    private final StockDataAccessMapper stockDataAccessMapper;

    public FavoriteStockEntity favoriteStockToFavoriteStockEntity(FavoriteStock favoriteStock) {
        return FavoriteStockEntity.builder()
                .memberId(favoriteStock.getMemberId())
                .stock(stockDataAccessMapper.stockToStockEntity(favoriteStock.getStockDetail().getStock()))
                .build();
    }

    public FavoriteStock favoriteStockEntityToFavoriteStock(FavoriteStockEntity favoriteStockEntity) {
        return FavoriteStock.builder()
                .id(favoriteStockEntity.getId())
                .memberId(favoriteStockEntity.getMemberId())
                .stockDetail(stockDataAccessMapper.stockEntityToStockDetail(favoriteStockEntity.getStock()))
                .build();
    }

    public List<FavoriteStock> favoriteStockEntitiesToFavoriteStocks(List<FavoriteStockEntity> favoriteStockEntities) {
        return favoriteStockEntities.stream()
                .map(this::favoriteStockEntityToFavoriteStock)
                .toList();
    }
}
