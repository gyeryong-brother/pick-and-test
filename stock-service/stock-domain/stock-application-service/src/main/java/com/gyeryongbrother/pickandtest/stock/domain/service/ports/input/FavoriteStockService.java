package com.gyeryongbrother.pickandtest.stock.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.DeleteFavoriteStockCommand;

public interface FavoriteStockService {

    CreateFavoriteStockResponse createFavoriteStock(CreateFavoriteStockCommand command);

    void deleteFavoriteStock(DeleteFavoriteStockCommand command);
}
