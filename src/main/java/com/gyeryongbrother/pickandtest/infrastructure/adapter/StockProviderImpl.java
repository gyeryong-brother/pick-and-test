package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange.NASDAQ;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockInformation;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockProviderImpl implements StockProvider {

    private final KoreaInvestmentClient koreaInvestmentClient;

    @Override
    public Stock provide(String symbol) {
        FetchStockResponse fetchStockResponse = koreaInvestmentClient.fetchStock(StockExchange.NASDAQ, symbol);
        StockInformation stockInformation = fetchStockResponse.stockInformation();
        List<StockPrice> stockPrices = getStockPrices(symbol);
        return Stock.builder()
                .name(stockInformation.productEnglishName())
                .symbol(symbol)
                .listingDate(parse(stockInformation.listingDate()))
                .stockPrices(stockPrices)
                .build();
    }

    private LocalDate parse(String listingDate) {
        if (listingDate.isBlank()) {
            return null;
        }
        return DateTimeHandler.toDate(listingDate);
    }

    private List<StockPrice> getStockPrices(String symbol) {
        StockPriceAssembler stockPriceAssembler = new StockPriceAssembler();
        while (stockPriceAssembler.hasNext()) {
            LocalDate nextDate = stockPriceAssembler.getNextDate();
            FetchStockPriceResponse fetchStockPriceResponse = fetchStockPriceInNasdaq(symbol, nextDate);
            stockPriceAssembler.add(fetchStockPriceResponse);
        }
        return stockPriceAssembler.getStockPrices();
    }

    private FetchStockPriceResponse fetchStockPriceInNasdaq(String symbol, LocalDate date) {
        return koreaInvestmentClient.fetchStockPrice(NASDAQ, symbol, date);
    }
}
