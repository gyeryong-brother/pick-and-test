package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockPriceDate;
import java.util.List;

public interface StockPriceQueryRepository {

    List<StockPrice> findAllByStockId(Long stockId);

    List<Long> findAllStockIds();

    StockPriceDate findLastDateOfStockPricesByStockId(Long stockId);
}
