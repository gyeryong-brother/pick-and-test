package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;

public interface StockDetailRepository {

    StockDetail save(StockDetail stockDetail);
}
