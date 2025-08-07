package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.message.publisher;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;

public interface StockMessagePublisher {

    void publishStockPriceCollectionRequestedEvent(Stock stock);
}
