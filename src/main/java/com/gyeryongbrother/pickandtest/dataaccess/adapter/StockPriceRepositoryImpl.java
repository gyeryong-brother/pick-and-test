package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockPriceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockPriceRepositoryImpl implements StockPriceRepository {

    private final StockPriceJpaRepository stockPriceJpaRepository;
    private final StockDataAccessMapper stockDataAccessMapper;

    @Override
    public StockPrice save(StockPrice stockPrice) {
        StockPriceEntity stockPriceEntity = stockDataAccessMapper.stockPricetoStockPriceEntity(stockPrice);
        StockPriceEntity savedStockPriceEntity = stockPriceJpaRepository.save(stockPriceEntity);
        return stockDataAccessMapper.stockPriceEntityToStockPrice(savedStockPriceEntity);
    }

}
