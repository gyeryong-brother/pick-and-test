package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceClientImpl implements StockPriceClient {

    @Override
    public List<StockPrice> fetchStockPrices(Long stockId) {
        return null;
    }
}
