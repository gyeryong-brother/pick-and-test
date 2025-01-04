package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import java.util.List;

public interface DividendQueryRepository {

    List<Dividend> findAllByStockId(Long stockId);
}
