package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;

import java.util.List;

public interface StockPriceQueryService {

    List<StockPriceResponse> findAllByStockId(Long stockId);
}
