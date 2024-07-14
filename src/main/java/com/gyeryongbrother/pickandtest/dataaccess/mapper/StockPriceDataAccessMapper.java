package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockPriceDataAccessMapper {

    public StockPriceEntity stockPricetoStockPriceEntity(StockPrice stockPrice) {
        StockEntity stockEntity = StockEntity.builder()
                .id(stockPrice.getStockId())
                .build();
        return StockPriceEntity.builder()
                .id(stockPrice.getId())
                .date(stockPrice.getDate())
                .price(stockPrice.getPrice())
                .stock(stockEntity)
                .build();
    }

    public StockPrice stockPriceEntityToStockPrice(StockPriceEntity stockPriceEntity) {
        return StockPrice.builder()
                .id(stockPriceEntity.getId())
                .stockId(stockPriceEntity.getStock().getId())
                .date(stockPriceEntity.getDate())
                .price(stockPriceEntity.getPrice())
                .build();
    }

    List<StockPrice> stockPriceEntitiesToStockPrices(java.util.List<StockPriceEntity> stockPriceEntities) {
        if (stockPriceEntities == null) {
            return java.util.List.of();
        }
        return stockPriceEntities.stream()
                .map(this::stockPriceEntityToStockPrice)
                .toList();
    }
}
