package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.time.LocalDate;
import java.util.List;

public interface StockPriceClient {

    List<StockPrice> fetchStockPrices(Stock stock, LocalDate startDate);
}
