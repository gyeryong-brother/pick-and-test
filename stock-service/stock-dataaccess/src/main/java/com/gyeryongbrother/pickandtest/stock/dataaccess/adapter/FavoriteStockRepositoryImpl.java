package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.FavoriteStockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.FavoriteStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.FavoriteStockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FavoriteStockRepositoryImpl implements FavoriteStockRepository {

    private final FavoriteStockJpaRepository favoriteStockJpaRepository;
    private final FavoriteStockDataAccessMapper favoriteStockDataAccessMapper;

    @Override
    public FavoriteStock save(FavoriteStock favoriteStock) {
        FavoriteStockEntity favoriteStockEntity =
                favoriteStockDataAccessMapper.favoriteStockToFavoriteStockEntity(favoriteStock);
        FavoriteStockEntity savedFavoriteStockEntity = favoriteStockJpaRepository.save(favoriteStockEntity);
        return favoriteStockDataAccessMapper.favoriteStockEntityToFavoriteStock(savedFavoriteStockEntity);
    }

    @Override
    public void delete(FavoriteStock favoriteStock) {
        favoriteStockJpaRepository.deleteById(favoriteStock.id());
    }
}
