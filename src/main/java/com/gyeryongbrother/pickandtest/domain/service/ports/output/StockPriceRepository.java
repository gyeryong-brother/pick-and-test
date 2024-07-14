package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;

public interface StockPriceRepository {

    StockPrice save(StockPrice stockPrice);
}
