package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;

import java.util.List;

public interface StockPriceQueryRepository {

    List<StockPriceResponse> findAllByStockId(Long stockId);
}
