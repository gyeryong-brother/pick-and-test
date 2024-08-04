package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.*;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockFetcherDataMapper {

    private final StockPriceFetcherDataMapper stockPriceFetcherDataMapper;
    private final DividendFetcherDataMapper dividendFetcherDataMapper;

    public StockDetail stockResponseToStockDetail(
            StockResponse stockResponse,
            String symbol,
            StockExchange stockExchange,
            List<StockPriceResponse> stockPriceResponses,
            DividendResponse dividendResponse
    ) {
        Stock stock = createStock(stockResponse, symbol, stockExchange);
        List<StockPrice> stockPrices =
                stockPriceFetcherDataMapper.stockPriceResponsesToStockPrices(stockPriceResponses);
        List<Dividend> dividends = dividendFetcherDataMapper.dividendResponseToDividends(dividendResponse);
        return createStockDetail(stock, stockPrices, dividends);
    }

    private Stock createStock(
            StockResponse stockResponse,
            String symbol,
            StockExchange stockExchange
    ) {
        return Stock.builder()
                .name(stockResponse.stockDetail().productEnglishName())
                .symbol(symbol)
                .stockExchange(stockExchange)
                .listingDate(parse(stockResponse.stockDetail().listingDate()))
                .build();
    }

    private LocalDate parse(String listingDate) {
        if (listingDate.isBlank()) {
            return null;
        }
        return DateTimeHandler.toDate(listingDate);
    }

    private StockDetail createStockDetail(
            Stock stock,
            List<StockPrice> stockPrices,
            List<Dividend> dividends
    ) {
        return StockDetail.builder()
                .stock(stock)
                .stockPrices(stockPrices)
                .dividends(dividends)
                .build();
    }
}
