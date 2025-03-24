package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockServiceClient {

    private final StockFetcher stockFetcher;

    public StockResponse fetchStock(Long stockId) {
        return stockFetcher.fetchStock(stockId);
    }
}
