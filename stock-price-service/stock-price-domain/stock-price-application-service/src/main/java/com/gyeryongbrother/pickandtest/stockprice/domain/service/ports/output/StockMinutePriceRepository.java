package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import java.util.List;

public interface StockMinutePriceRepository {

    void saveAll(List<StockMinutePrice> stockMinutePrices);
}
