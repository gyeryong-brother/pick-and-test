package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Stock;

public interface StockProvider {

    Stock provide(String symbol);
}
