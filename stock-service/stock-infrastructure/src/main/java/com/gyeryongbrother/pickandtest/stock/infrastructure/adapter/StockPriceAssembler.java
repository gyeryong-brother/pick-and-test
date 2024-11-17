package com.gyeryongbrother.pickandtest.stock.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceDetail;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class StockPriceAssembler {

    private final List<StockPriceResponse> stockPriceResponses;

    public StockPriceAssembler() {
        stockPriceResponses = new ArrayList<>();
    }

    public boolean hasNext() {
        if (stockPriceResponses.isEmpty()) {
            return true;
        }
        StockPriceResponse lastStockPriceResponse = getLastStockPriceResponse();
        return lastStockPriceResponse.continuityCode().hasNext();
    }

    private StockPriceResponse getLastStockPriceResponse() {
        int lastIndex = stockPriceResponses.size() - 1;
        return stockPriceResponses.get(lastIndex);
    }

    public LocalDate getNextDate() {
        if (stockPriceResponses.isEmpty()) {
            return LocalDate.now();
        }
        String lastDate = getLastStockDetail().date();
        return DateTimeHandler.toDate(lastDate)
                .minusDays(1);
    }

    private StockPriceDetail getLastStockDetail() {
        StockPriceBody lastStockPriceBody = getLastStockPriceResponse().stockPriceBody();
        List<StockPriceDetail> stockPriceDetails = lastStockPriceBody.stockPriceDetails();
        int lastIndex = stockPriceDetails.size() - 1;
        return stockPriceDetails.get(lastIndex);
    }

    public void add(StockPriceResponse stockPriceResponse) {
        stockPriceResponses.add(stockPriceResponse);
    }
}
