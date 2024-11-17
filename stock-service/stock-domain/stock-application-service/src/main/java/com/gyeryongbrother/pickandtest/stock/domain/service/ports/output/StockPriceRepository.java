package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;

public interface StockPriceRepository {

    StockPrice save(StockPrice stockPrice);
}
