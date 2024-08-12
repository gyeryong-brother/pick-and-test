package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final FavoriteStockRepository favoriteStockRepository;
    private final StockProvider stockProvider;
    private final StockRepository stockRepository;

    @Override
    public CreateFavoriteStockResponse createFavoriteStock(CreateFavoriteStockCommand createFavoriteStockCommand) {
        FavoriteStock favoriteStock = createFavoriteStockCommand.toDomain();
        FavoriteStock savedFavoriteStock = favoriteStockRepository.save(favoriteStock);
        return CreateFavoriteStockResponse.from(savedFavoriteStock);
    }

    @Override
    public void saveAll() {
        Arrays.stream(StockExchange.values())
                .map(stockProvider::getStockDetailsByStockExchange)
                .flatMap(List::stream)
                .forEach(stockRepository::save);
    }
}
