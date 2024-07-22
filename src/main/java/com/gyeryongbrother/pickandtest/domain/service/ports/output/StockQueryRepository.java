package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import java.util.List;

public interface StockQueryRepository {

    StockDetail findById(Long id);

    List<StockResponse> findAllByNameOrSymbol(String keyword);
}
