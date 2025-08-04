package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;

public interface StockMessageListener {

    void stockCreated(Stock stock);

    void collectStockMinutePrices(Stock stock);
}
