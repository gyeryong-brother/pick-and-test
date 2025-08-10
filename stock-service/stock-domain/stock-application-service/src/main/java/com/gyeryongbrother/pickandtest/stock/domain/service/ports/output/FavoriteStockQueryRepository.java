package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, transactionManager = "slaveTransactionManager")
public interface FavoriteStockQueryRepository {

    List<FavoriteStock> findAllByMemberId(Long memberId);

    FavoriteStock getById(Long id);

    Optional<FavoriteStock> findByStockIdAndMemberId(Long stockId, Long memberId);

    FavoriteStock getByStockIdAndMemberId(Long stockId, Long memberId);
}
