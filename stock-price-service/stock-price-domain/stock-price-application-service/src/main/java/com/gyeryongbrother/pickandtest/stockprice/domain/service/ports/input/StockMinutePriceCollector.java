package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input;

public interface StockMinutePriceCollector {

    void collectStockMinutePrices(Long stockId);
}
