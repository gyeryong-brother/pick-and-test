package com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.mapper.StockPriceDataAccessMapper;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockPriceRepositoryImpl implements StockPriceRepository {

    private final StockPriceJpaRepository stockPriceJpaRepository;
    private final StockPriceDataAccessMapper stockPriceDataAccessMapper;

    @Override
    public StockPrice save(StockPrice stockPrice) {
        StockPriceEntity stockPriceEntity = stockPriceDataAccessMapper.stockPriceToStockPriceEntity(stockPrice);
        StockPriceEntity savedStockPriceEntity = stockPriceJpaRepository.save(stockPriceEntity);
        return savedStockPriceEntity.toDomain();
    }
}
