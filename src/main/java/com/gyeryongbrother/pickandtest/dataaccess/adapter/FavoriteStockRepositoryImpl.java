package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.FavoriteStockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.FavoriteStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.FavoriteStockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockRepository;
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
}
