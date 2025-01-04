package com.gyeryongbrother.pickandtest.stock.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;

public interface StockService {

    CreateFavoriteStockResponse createFavoriteStock(CreateFavoriteStockCommand createFavoriteStockCommand);
}
