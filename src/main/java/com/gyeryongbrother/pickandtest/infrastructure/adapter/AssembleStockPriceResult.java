package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceInformation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssembleStockPriceResult {

    private final List<FetchStockPriceResponse> stockPrices;

    public AssembleStockPriceResult() {
        stockPrices = new ArrayList<>();
    }

    public boolean hasNext() {
        if (stockPrices.isEmpty()) {
            return true;
        }
        FetchStockPriceResponse lastFetchStockPriceResponse = getLastFetchStockPriceResponse();
        return lastFetchStockPriceResponse.continuityCode().hasNext();
    }

    private FetchStockPriceResponse getLastFetchStockPriceResponse() {
        int lastIndex = stockPrices.size() - 1;
        return stockPrices.get(lastIndex);
    }

    public LocalDate getNextDate() {
        if (stockPrices.isEmpty()) {
            return LocalDate.now();
        }
        String lastDate = getLastStockInformation().date();
        return DateTimeHandler.toDate(lastDate)
                .minusDays(1);
    }

    private StockPriceInformation getLastStockInformation() {
        StockPriceBody lastStockPriceBody = getLastFetchStockPriceResponse().stockPriceBody();
        List<StockPriceInformation> stockPriceInformations = lastStockPriceBody.stockPriceInformation();
        int lastIndex = stockPriceInformations.size() - 1;
        return stockPriceInformations.get(lastIndex);
    }

    public void add(FetchStockPriceResponse fetchStockPriceResponse) {
        stockPrices.add(fetchStockPriceResponse);
    }

    public List<StockPrice> getStockPrices() {
        return stockPrices.stream()
                .map(FetchStockPriceResponse::stockPriceBody)
                .map(StockPriceBody::stockPriceInformation)
                .flatMap(List::stream)
                .map(this::stockPriceInformationToStockPrice)
                .toList();
    }

    private StockPrice stockPriceInformationToStockPrice(StockPriceInformation stockPriceInformation) {
        return StockPrice.builder()
                .date(DateTimeHandler.toDate(stockPriceInformation.date()))
                .price(new BigDecimal(stockPriceInformation.price()))
                .build();
    }
}
