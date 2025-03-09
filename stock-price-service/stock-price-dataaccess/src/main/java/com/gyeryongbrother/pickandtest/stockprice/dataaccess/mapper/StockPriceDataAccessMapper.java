package com.gyeryongbrother.pickandtest.stockprice.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import org.springframework.stereotype.Component;

@Component
public class StockPriceDataAccessMapper {

    public StockPriceEntity stockPriceToStockPriceEntity(StockPrice stockPrice) {
        return new StockPriceEntity(
                stockPrice.id(),
                stockPrice.stockId(),
                stockPrice.date(),
                stockPrice.price()
        );
    }
}
