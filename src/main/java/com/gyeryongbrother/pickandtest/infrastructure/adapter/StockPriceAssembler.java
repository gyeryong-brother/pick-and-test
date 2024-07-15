package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceDetail;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class StockPriceAssembler {

    private final List<FetchStockPriceResponse> fetchStockPriceResponses;

    public StockPriceAssembler() {
        fetchStockPriceResponses = new ArrayList<>();
    }

    public boolean hasNext() {
        if (fetchStockPriceResponses.isEmpty()) {
            return true;
        }
        FetchStockPriceResponse lastFetchStockPriceResponse = getLastFetchStockPriceResponse();
        return lastFetchStockPriceResponse.continuityCode().hasNext();
    }

    private FetchStockPriceResponse getLastFetchStockPriceResponse() {
        int lastIndex = fetchStockPriceResponses.size() - 1;
        return fetchStockPriceResponses.get(lastIndex);
    }

    public LocalDate getNextDate() {
        if (fetchStockPriceResponses.isEmpty()) {
            return LocalDate.now();
        }
        String lastDate = getLastStockDetail().date();
        return DateTimeHandler.toDate(lastDate)
                .minusDays(1);
    }

    private StockPriceDetail getLastStockDetail() {
        StockPriceBody lastStockPriceBody = getLastFetchStockPriceResponse().stockPriceBody();
        List<StockPriceDetail> stockPriceDetails = lastStockPriceBody.stockPriceDetails();
        int lastIndex = stockPriceDetails.size() - 1;
        return stockPriceDetails.get(lastIndex);
    }

    public void add(FetchStockPriceResponse fetchStockPriceResponse) {
        fetchStockPriceResponses.add(fetchStockPriceResponse);
    }
}
