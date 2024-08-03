package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;

public interface StockService {

    CreateFavoriteStockResponse createFavoriteStock(CreateFavoriteStockCommand createFavoriteStockCommand);

    void saveAll();
}
