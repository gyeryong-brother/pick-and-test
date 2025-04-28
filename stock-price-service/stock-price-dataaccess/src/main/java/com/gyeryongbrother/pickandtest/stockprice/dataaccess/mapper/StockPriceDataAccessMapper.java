package com.gyeryongbrother.pickandtest.stockprice.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockPriceDataAccessMapper {

    public StockPriceEntity stockPriceToStockPriceEntity(StockPrice stockPrice) {
        return new StockPriceEntity(
                null,
                stockPrice.stockId(),
                stockPrice.date(),
                stockPrice.price()
        );
    }

    public List<StockPriceEntity> stockPricesToStockPriceEntities(List<StockPrice> stockPrices) {
        return stockPrices.stream()
                .map(this::stockPriceToStockPriceEntity)
                .toList();
    }
}
