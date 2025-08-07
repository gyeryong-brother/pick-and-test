package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;

public interface StockMessageListener {

    void stockPriceCollectionRequested(Stocks stocks);

    void collectStockMinutePrices(Stock stock);
}
