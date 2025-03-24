package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;

public interface StockClient {

    Stock fetchStock(Long stockId);
}
