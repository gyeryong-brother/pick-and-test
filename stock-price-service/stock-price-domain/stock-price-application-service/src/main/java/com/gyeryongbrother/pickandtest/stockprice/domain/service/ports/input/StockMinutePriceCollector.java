package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;

public interface StockMinutePriceCollector {

    void collectStockMinutePrices(Stocks stocks);
}
