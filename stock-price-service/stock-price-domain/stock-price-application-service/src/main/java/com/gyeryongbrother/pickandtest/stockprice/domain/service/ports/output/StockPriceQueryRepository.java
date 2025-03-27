package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockPriceDate;
import java.time.LocalDate;
import java.util.List;

public interface StockPriceQueryRepository {

    List<StockPrice> findAllByStockId(Long stockId);

    StockPriceDate findLastDateOfStockPricesByStockId(Long stockId);
}
