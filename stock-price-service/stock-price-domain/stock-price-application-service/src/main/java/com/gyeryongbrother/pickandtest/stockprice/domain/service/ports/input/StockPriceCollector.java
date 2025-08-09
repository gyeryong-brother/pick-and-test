package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;

public interface StockPriceCollector {

    void collectStockPrices(Stocks stocks);
}
