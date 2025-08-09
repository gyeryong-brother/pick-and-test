package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import java.time.LocalDate;
import java.util.List;

public interface StockPriceFetcher {

    List<StockPrice> fetchStockPrices(Stocks stocks, LocalDate startDate);

    List<StockMinutePrice> fetchStockMinutePrices(Stocks stocks, LocalDate startDate);
}
