package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Stock;

public interface StockQueryRepository {

    Stock findById(Long id);
}
