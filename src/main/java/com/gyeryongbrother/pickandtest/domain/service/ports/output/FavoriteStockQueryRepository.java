package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import java.util.List;

public interface FavoriteStockQueryRepository {

    List<FavoriteStock> findAllByMemberId(Long memberId);
}
