package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;

import java.util.List;

public interface DividendQueryRepository {

    List<Dividend> findAllByStockId(Long stockId);
}
