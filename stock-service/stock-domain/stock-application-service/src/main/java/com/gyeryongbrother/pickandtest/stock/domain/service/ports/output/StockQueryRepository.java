package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.util.List;

public interface StockQueryRepository {

    Stock getById(Long id);

    List<Stock> findAllByNameOrSymbol(String keyword);

    List<String> findAllSymbolsByStockExchange(StockExchange stockExchange);
}
