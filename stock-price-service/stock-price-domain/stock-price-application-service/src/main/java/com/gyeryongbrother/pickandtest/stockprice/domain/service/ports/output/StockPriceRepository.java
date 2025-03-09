package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;

public interface StockPriceRepository {

    StockPrice save(StockPrice stockPrice);
}
