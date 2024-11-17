package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.util.List;

public interface StockProvider {

    List<StockDetail> getStockDetailsByStockExchange(StockExchange stockExchange);
}
