package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.util.List;

public interface SymbolFetcher {

    List<String> fetchSymbol(StockExchange stockExchange);
}
