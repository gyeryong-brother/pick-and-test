package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceDetail;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockPriceResponses {

    private final List<StockPriceResponse> stockPrices = new ArrayList<>();

    public boolean isEmpty() {
        return stockPrices.isEmpty();
    }

    public int size() {
        return stockPrices.size();
    }

    public void add(StockPriceResponse stockPriceResponse) {
        stockPrices.add(stockPriceResponse);
    }

    public StockPriceResponse lastStockPrice() {
        int lastIndex = size() - 1;
        return stockPrices.get(lastIndex);
    }

    public StockPriceDetail lastStockPriceDetail() {
        StockPriceBody lastStockPriceBody = lastStockPrice().stockPriceBody();
        List<StockPriceDetail> stockPriceDetails = lastStockPriceBody.stockPriceDetails();
        int lastIndex = stockPriceDetails.size() - 1;
        return stockPriceDetails.get(lastIndex);
    }

    public List<StockPrice> stockPrices(Long stockId, LocalDate startDate) {
        return stockPrices.stream()
                .map(StockPriceResponse::stockPriceBody)
                .map(StockPriceBody::stockPriceDetails)
                .flatMap(List::stream)
                .map(it -> it.toDomain(stockId))
                .filter(it -> !it.date().isBefore(startDate))
                .toList();
    }
}
