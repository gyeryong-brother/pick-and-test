package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange.NASDAQ;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.StockFetcherDataMapper;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockProviderImpl implements StockProvider {

    private final KoreaInvestmentClient koreaInvestmentClient;
    private final StockFetcherDataMapper stockFetcherDataMapper;

    @Override
    public Stock provide(String symbol) {
        FetchStockResponse fetchStockResponse = koreaInvestmentClient.fetchStock(StockExchange.NASDAQ, symbol);
        List<FetchStockPriceResponse> fetchStockPriceResponses = getFetchStockPriceResponses(symbol);
        return stockFetcherDataMapper.fetchStockResponseToStock(fetchStockResponse, symbol, fetchStockPriceResponses);
    }

    private List<FetchStockPriceResponse> getFetchStockPriceResponses(String symbol) {
        StockPriceAssembler stockPriceAssembler = new StockPriceAssembler();
        while (stockPriceAssembler.hasNext()) {
            LocalDate nextDate = stockPriceAssembler.getNextDate();
            FetchStockPriceResponse fetchStockPriceResponse = fetchStockPriceInNasdaq(symbol, nextDate);
            stockPriceAssembler.add(fetchStockPriceResponse);
        }
        return stockPriceAssembler.getFetchStockPriceResponses();
    }

    private FetchStockPriceResponse fetchStockPriceInNasdaq(String symbol, LocalDate date) {
        return koreaInvestmentClient.fetchStockPrice(NASDAQ, symbol, date);
    }
}
