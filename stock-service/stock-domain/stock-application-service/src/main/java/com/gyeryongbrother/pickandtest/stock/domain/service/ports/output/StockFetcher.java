package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.util.Optional;

public interface StockFetcher {

    Optional<Stock> fetchStock(StockExchange stockExchange, String symbol);
}
