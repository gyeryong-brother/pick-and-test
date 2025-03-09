package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

public interface StockPriceCollector {

    void collectStockPrices(Long stockId);
}
