package com.gyeryongbrother.pickandtest.stock.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockDetailResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockResponse;
import java.util.List;

public interface StockQueryService {

    List<MarketCapitalizationResponse> findAllMarketCapitalizationsByStockId(Long stockId);

    List<StockResponse> findAllByNameOrSymbol(String keyword);

    StockDetailResponse findStockById(Long stockId);
}
