package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;

public interface FavoriteStockRepository {

    FavoriteStock save(FavoriteStock favoriteStock);
}
