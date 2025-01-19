package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import java.util.List;

public interface FavoriteStockQueryRepository {

    List<FavoriteStock> findAllByMemberId(Long memberId);

    FavoriteStock findById(Long id);

    FavoriteStock findByStockIdAndMemberId(Long stockId, Long memberId);
}
