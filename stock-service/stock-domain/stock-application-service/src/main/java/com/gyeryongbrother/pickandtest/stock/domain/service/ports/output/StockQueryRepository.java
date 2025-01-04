package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockWithPrices;
import java.util.List;

public interface StockQueryRepository {

    StockWithPrices findStockWithPricesById(Long id);

    StockDetail findById(Long id);

    List<Stock> findAllByNameOrSymbol(String keyword);
}
