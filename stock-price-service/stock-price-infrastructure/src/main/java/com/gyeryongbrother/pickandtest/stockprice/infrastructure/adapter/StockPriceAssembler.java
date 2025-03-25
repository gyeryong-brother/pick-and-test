package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceDetail;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class StockPriceAssembler {

    private final LocalDate startDate;
    private final DateProvider dateProvider;
    private final List<StockPriceResponse> stockPriceResponses;

    public StockPriceAssembler(LocalDate startDate, DateProvider dateProvider) {
        this.startDate = startDate;
        this.dateProvider = dateProvider;
        stockPriceResponses = new ArrayList<>();
    }

    public boolean hasNext() {
        LocalDate now = dateProvider.now();
        if (now.isBefore(startDate)) {
            return false;
        }
        if (stockPriceResponses.isEmpty()) {
            return true;
        }
        StockPriceResponse lastStockPriceResponse = getLastStockPriceResponse();
        if (!getLastDate().isAfter(startDate)) {
            return false;
        }
        return lastStockPriceResponse.continuityCode().hasNext();
    }

    private StockPriceResponse getLastStockPriceResponse() {
        int lastIndex = stockPriceResponses.size() - 1;
        return stockPriceResponses.get(lastIndex);
    }

    private LocalDate getLastDate() {
        String lastDate = getLastStockDetail().date();
        return DateTimeHandler.toDate(lastDate);
    }

    public LocalDate getNextDate() {
        if (stockPriceResponses.isEmpty()) {
            return dateProvider.now();
        }
        return getLastDate().minusDays(1);
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

    public List<StockPrice> stockPrices(Long stockId) {
        return stockPriceResponses.stream()
                .map(StockPriceResponse::stockPriceBody)
                .map(StockPriceBody::stockPriceDetails)
                .flatMap(List::stream)
                .map(it -> it.toDomain(stockId))
                .filter(it -> !it.date().isBefore(startDate))
                .toList();
    }
}
