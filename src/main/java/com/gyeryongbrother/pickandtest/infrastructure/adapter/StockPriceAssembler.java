package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceInformation;
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
        fetchStockPriceResponses.add(fetchStockPriceResponse);
    }
}
