package com.gyeryongbrother.pickandtest.stock.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.FavoriteStockEntity;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteStockDataAccessMapper {

    private final StockDataAccessMapper stockDataAccessMapper;

    public FavoriteStockEntity favoriteStockToFavoriteStockEntity(FavoriteStock favoriteStock) {
        return FavoriteStockEntity.builder()
                .memberId(favoriteStock.memberId())
                .stock(stockDataAccessMapper.stockToStockEntity(favoriteStock.stock()))
                .build();
    }

    public FavoriteStock favoriteStockEntityToFavoriteStock(FavoriteStockEntity favoriteStockEntity) {
        return FavoriteStock.builder()
                .id(favoriteStockEntity.getId())
                .memberId(favoriteStockEntity.getMemberId())
                .stock(stockDataAccessMapper.stockEntityToStock(favoriteStockEntity.getStock()))
                .build();
    }

    public List<FavoriteStock> favoriteStockEntitiesToFavoriteStocks(List<FavoriteStockEntity> favoriteStockEntities) {
        return favoriteStockEntities.stream()
                .map(this::favoriteStockEntityToFavoriteStock)
                .toList();
    }
}
