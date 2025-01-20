package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import java.util.List;
import java.util.Optional;

public interface FavoriteStockQueryRepository {

    List<FavoriteStock> findAllByMemberId(Long memberId);

    FavoriteStock findById(Long id);

    Optional<FavoriteStock> findByStockIdAndMemberId(Long stockId, Long memberId);
}
