package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.time.LocalDate;
import java.util.List;

public interface StockPriceFetcher {

    List<StockPrice> fetchStockPrices(Long stockId, LocalDate startDate);

    List<StockMinutePrice> fetchStockMinutePrices(Long stockId, LocalDate startDate);
}
