package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.command;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockDetailEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockDetailJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockDetailRepositoryImpl implements StockDetailRepository {

    private final StockDetailJpaRepository stockDetailJpaRepository;

    @Override
    public StockDetail save(StockDetail stockDetail) {
        StockDetailEntity stockDetailEntity = new StockDetailEntity(
                null,
                StockEntity.builder()
                        .id(stockDetail.stockId())
                        .build(),
                stockDetail.lastStockPrice(),
                stockDetail.compoundAnnualGrowthRate(),
                stockDetail.dividendYield()
        );
        StockDetailEntity savedStockDetailEntity = stockDetailJpaRepository.save(stockDetailEntity);
        return savedStockDetailEntity.toDomain();
    }
}
