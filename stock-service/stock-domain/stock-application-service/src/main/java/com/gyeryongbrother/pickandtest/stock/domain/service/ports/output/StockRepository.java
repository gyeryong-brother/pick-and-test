package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;

public interface StockRepository {

    Stock save(Stock stock);
}
