package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.util.List;

public interface StockPriceQueryRepository {

    List<StockPrice> findAllByStockId(Long stockId);
}
