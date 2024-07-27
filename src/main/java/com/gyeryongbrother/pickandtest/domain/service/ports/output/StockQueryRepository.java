package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import java.util.List;

public interface StockQueryRepository {

    Stock findById(Long id);

    List<StockResponse> findAllByNameOrSymbol(String keyword);
}
