package com.gyeryongbrother.pickandtest.stock.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponse;
import java.util.List;

public interface FavoriteStockQueryService {

    List<FavoriteStockResponse> findAllFavoriteStocksByMemberId(Long memberId);

    FavoriteStockResponse findFavoriteStockByStockIdAndMemberId(Long stockId, Long memberId);
}
