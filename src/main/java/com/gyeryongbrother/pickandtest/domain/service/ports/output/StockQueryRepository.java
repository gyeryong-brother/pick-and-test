package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import java.util.List;

public interface StockQueryRepository {

    StockDetail findById(Long id);

    List<Stock> findAllByNameOrSymbol(String keyword);
}
