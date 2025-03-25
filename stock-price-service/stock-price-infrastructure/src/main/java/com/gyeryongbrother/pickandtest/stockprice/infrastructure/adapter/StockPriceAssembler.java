package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class StockPriceAssembler {

    private final LocalDate startDate;
    private final DateProvider dateProvider;
    private final StockPriceResponses stockPriceResponses;
    private final Function<LocalDate, StockPriceResponse> fetchingFunction;

    public StockPriceAssembler(
            LocalDate startDate,
            DateProvider dateProvider,
            Function<LocalDate, StockPriceResponse> fetchingFunction
    ) {
        this.startDate = startDate;
        this.dateProvider = dateProvider;
        stockPriceResponses = new StockPriceResponses();
        this.fetchingFunction = fetchingFunction;
    }

    public List<StockPrice> assemble(Long stockId) {
        while (hasNext()) {
            LocalDate nextDate = getNextDate();
            StockPriceResponse stockPriceResponse = fetchingFunction.apply(nextDate);
            add(stockPriceResponse);
        }
        return stockPrices(stockId);
    }

    public boolean hasNext() {
        LocalDate now = dateProvider.now();
        if (now.isBefore(startDate)) {
            return false;
        }
        if (stockPriceResponses.isEmpty()) {
            return true;
        }
        StockPriceResponse lastStockPriceResponse = stockPriceResponses.lastStockPrice();
        if (!getLastDate().isAfter(startDate)) {
            return false;
        }
        return lastStockPriceResponse.continuityCode().hasNext();
    }

    private LocalDate getLastDate() {
        String lastDate = stockPriceResponses.lastStockPriceDetail().date();
        return DateTimeHandler.toDate(lastDate);
    }

    public LocalDate getNextDate() {
        if (stockPriceResponses.isEmpty()) {
            return dateProvider.now();
        }
        return getLastDate().minusDays(1);
    }

    public void add(StockPriceResponse stockPriceResponse) {
        stockPriceResponses.add(stockPriceResponse);
    }

    public List<StockPrice> stockPrices(Long stockId) {
        return stockPriceResponses.stockPrices(stockId, startDate);
    }
}
