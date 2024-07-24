package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final FavoriteStockRepository favoriteStockRepository;

    @Override
    public CreateFavoriteStockResponse createFavoriteStock(CreateFavoriteStockCommand createFavoriteStockCommand) {
        FavoriteStock favoriteStock = createFavoriteStockCommand.toDomain();
        FavoriteStock savedFavoriteStock = favoriteStockRepository.save(favoriteStock);
        return CreateFavoriteStockResponse.from(savedFavoriteStock);
    }
}
