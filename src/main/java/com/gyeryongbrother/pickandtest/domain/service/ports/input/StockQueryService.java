package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import java.util.List;

public interface StockQueryService {

    List<MarketCapitalizationResponse> findAllMarketCapitalizationsByStockId(Long stockId);

    List<StockResponse> findAllByNameOrSymbol(String keyword);

    List<FavoriteStockResponse> findAllFavoriteStocksByMemberId(Long memberId);
}
