package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.StockServiceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockClientImpl implements StockClient {

    private final StockServiceClient stockServiceClient;

    @Override
    public Stock fetchStock(Long stockId) {
        StockResponse stockResponse = stockServiceClient.fetchStock(stockId);
        return new Stock(
                stockResponse.id(),
                stockResponse.symbol(),
                StockExchange.valueOf(stockResponse.stockExchange())
        );
    }
}
