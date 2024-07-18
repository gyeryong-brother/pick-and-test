package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.AlphaVantageClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.NasdaqClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockSymbolDetail;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockSymbolResponse;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.StockFetcherDataMapper;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockProviderImpl implements StockProvider {

    private final KoreaInvestmentClient koreaInvestmentClient;
    private final AlphaVantageClient alphaVantageClient;
    private final NasdaqClient nasdaqClient;
    private final StockFetcherDataMapper stockFetcherDataMapper;

    @Override
    public List<Stock> getStocksByStockExchange(StockExchange stockExchange) {
        StockSymbolResponse stockSymbolResponse = nasdaqClient.fetchStockSymbol(stockExchange);
        List<StockSymbolDetail> stockSymbolDetails = stockSymbolResponse.stockSymbolDetails();
        return stockSymbolDetails.stream()
                .map(StockSymbolDetail::symbol)
                .map(it -> getStock(stockExchange, it))
                .toList();
    }

    private Stock getStock(StockExchange stockExchange, String symbol) {
        StockResponse stockResponse = koreaInvestmentClient.fetchStock(stockExchange, symbol);
        List<StockPriceResponse> stockPriceResponses = assembleStockPriceResponses(stockExchange, symbol);
        DividendResponse dividendResponse = alphaVantageClient.fetchDividend(symbol);
        return stockFetcherDataMapper.stockResponseToStock(
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
