package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import java.util.List;

public interface StockPriceQueryRepository {

    List<StockPrice> findAllByStockId(Long stockId);
}
