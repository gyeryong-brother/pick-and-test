package com.gyeryongbrother.pickandtest.stock.infrastructure.client;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.util.Optional;

public interface StockClient {

    Optional<Stock> fetchStock(StockExchange stockExchange, String symbol);
}
