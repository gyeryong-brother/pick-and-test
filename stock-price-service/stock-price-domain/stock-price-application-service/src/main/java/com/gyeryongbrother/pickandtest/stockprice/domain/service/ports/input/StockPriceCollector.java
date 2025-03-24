package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;

public interface StockPriceCollector {

    void collectStockPrices(Stock stock);
}
