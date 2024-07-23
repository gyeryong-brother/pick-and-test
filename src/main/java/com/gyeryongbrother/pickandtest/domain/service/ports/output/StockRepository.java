package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;

public interface StockRepository {

    Stock save(Stock stock);

    StockDetail save(StockDetail stockDetail);
}
