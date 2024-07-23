package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    private final StockDataAccessMapper stockDataAccessMapper;

    @Override
    public Stock save(Stock stock) {
        StockEntity stockEntity = stockDataAccessMapper.stockToStockEntity(stock);
        StockEntity savedStockEntity = stockJpaRepository.save(stockEntity);
        return stockDataAccessMapper.stockEntityToStock(savedStockEntity);
    }

    @Override
    public StockDetail save(StockDetail stockDetail) {
        StockEntity stockEntity = stockDataAccessMapper.stockDetailToStockEntity(stockDetail);
        StockEntity savedStockEntity = stockJpaRepository.save(stockEntity);
        return stockDataAccessMapper.stockEntityToStockDetail(savedStockEntity);
    }
}
