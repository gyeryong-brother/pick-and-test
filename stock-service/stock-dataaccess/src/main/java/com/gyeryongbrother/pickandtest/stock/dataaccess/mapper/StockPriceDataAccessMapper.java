package com.gyeryongbrother.pickandtest.stock.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockPriceDataAccessMapper {

    public StockPriceEntity stockPriceToStockPriceEntity(StockPrice stockPrice) {
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

    public List<StockPrice> stockPriceEntitiesToStockPrices(List<StockPriceEntity> stockPriceEntities) {
        return stockPriceEntities.stream()
                .map(this::stockPriceEntityToStockPrice)
                .toList();
    }
}
