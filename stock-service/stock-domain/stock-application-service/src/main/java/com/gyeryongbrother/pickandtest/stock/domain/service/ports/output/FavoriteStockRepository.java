package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;

public interface FavoriteStockRepository {

    FavoriteStock save(FavoriteStock favoriteStock);

    void delete(FavoriteStock favoriteStock);
}
