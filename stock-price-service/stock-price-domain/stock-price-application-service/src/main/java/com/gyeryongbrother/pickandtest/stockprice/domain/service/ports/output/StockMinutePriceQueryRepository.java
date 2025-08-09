package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import java.time.LocalDate;

public interface StockMinutePriceQueryRepository {

    LocalDate findLastDateOfStockMinutePricesByStockId(Long stockId);
}
