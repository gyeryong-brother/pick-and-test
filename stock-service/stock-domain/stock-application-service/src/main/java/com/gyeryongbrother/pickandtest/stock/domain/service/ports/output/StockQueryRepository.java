package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import java.util.List;

public interface StockQueryRepository {

    Stock getById(Long id);

    List<Stock> findAllByNameOrSymbol(String keyword);
}
