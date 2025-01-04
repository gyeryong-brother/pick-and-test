package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;

public interface StockRepository {

    Stock save(Stock stock);

    StockDetail save(StockDetail stockDetail);
}
