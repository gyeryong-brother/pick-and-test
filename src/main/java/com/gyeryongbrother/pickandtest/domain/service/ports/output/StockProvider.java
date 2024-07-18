package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import java.util.List;

public interface StockProvider {

    List<Stock> getStocksByStockExchange(StockExchange stockExchange);
}
