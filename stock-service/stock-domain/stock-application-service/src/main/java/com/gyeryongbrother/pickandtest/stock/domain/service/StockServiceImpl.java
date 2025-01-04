package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
