package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange.NASDAQ;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
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
        StockResponse stockResponse = koreaInvestmentClient.fetchStock(StockExchange.NASDAQ, symbol);
        List<StockPriceResponse> stockPriceResponses = getStockPriceResponses(symbol);
        return stockFetcherDataMapper.stockResponseToStock(stockResponse, symbol, stockPriceResponses);
    }

    private List<StockPriceResponse> getStockPriceResponses(String symbol) {
        StockPriceAssembler stockPriceAssembler = new StockPriceAssembler();
        while (stockPriceAssembler.hasNext()) {
            LocalDate nextDate = stockPriceAssembler.getNextDate();
            StockPriceResponse stockPriceResponse = fetchStockPriceInNasdaq(symbol, nextDate);
            stockPriceAssembler.add(stockPriceResponse);
        }
        return stockPriceAssembler.getStockPriceResponses();
    }

    private StockPriceResponse fetchStockPriceInNasdaq(String symbol, LocalDate date) {
        return koreaInvestmentClient.fetchStockPrice(NASDAQ, symbol, date);
    }
}
