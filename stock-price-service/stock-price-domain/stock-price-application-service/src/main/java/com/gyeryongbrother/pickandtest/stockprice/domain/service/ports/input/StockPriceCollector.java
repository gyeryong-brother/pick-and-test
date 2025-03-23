package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input;

public interface StockPriceCollector {

    void collectStockPrices(Long stockId);
}
