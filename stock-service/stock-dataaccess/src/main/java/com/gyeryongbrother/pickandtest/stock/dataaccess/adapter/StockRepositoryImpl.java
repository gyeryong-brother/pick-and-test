package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
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
