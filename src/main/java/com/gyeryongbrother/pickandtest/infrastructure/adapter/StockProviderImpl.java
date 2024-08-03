package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.NasdaqClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolDetail;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolResponse;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.StockFetcherDataMapper;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockProviderImpl implements StockProvider {

    private final KoreaInvestmentClient koreaInvestmentClient;
    private final NasdaqClient nasdaqClient;
    private final StockFetcherDataMapper stockFetcherDataMapper;

    @Override
    public List<StockDetail> getStockDetailsByStockExchange(StockExchange stockExchange) {
        StockSymbolResponse stockSymbolResponse = nasdaqClient.fetchStockSymbol(stockExchange);
        List<StockSymbolDetail> stockSymbolDetails = stockSymbolResponse.stockSymbolDetails();
        return stockSymbolDetails.stream()
                .map(StockSymbolDetail::symbol)
                .map(it -> getStockDetail(stockExchange, it))
                .toList();
    }

    private StockDetail getStockDetail(StockExchange stockExchange, String symbol) {
        StockResponse stockResponse = koreaInvestmentClient.fetchStock(stockExchange, symbol);
        List<StockPriceResponse> stockPriceResponses = assembleStockPriceResponses(stockExchange, symbol);
        DividendResponse dividendResponse = nasdaqClient.fetchDividend(symbol, "stocks");
        return stockFetcherDataMapper.stockResponseToStockDetail(
                stockResponse,
                symbol,
                stockExchange,
                stockPriceResponses,
                dividendResponse
        );
    }

    private List<StockPriceResponse> assembleStockPriceResponses(StockExchange stockExchange, String symbol) {
        StockPriceAssembler stockPriceAssembler = new StockPriceAssembler();
        while (stockPriceAssembler.hasNext()) {
            LocalDate nextDate = stockPriceAssembler.getNextDate();
            StockPriceResponse stockPriceResponse =
                    koreaInvestmentClient.fetchStockPrice(stockExchange, symbol, nextDate);
            stockPriceAssembler.add(stockPriceResponse);
        }
        return stockPriceAssembler.getStockPriceResponses();
    }
}
