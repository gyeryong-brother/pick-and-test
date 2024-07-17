package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockDetail;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockFetcherDataMapper {

    private final StockPriceFetcherDataMapper stockPriceFetcherDataMapper;
    private final DividendFetcherDataMapper dividendFetcherDataMapper;

    public Stock stockResponseToStock(
            StockResponse stockResponse,
            String symbol,
            List<StockPriceResponse> stockPriceResponses,
            DividendResponse dividendResponse
    ) {
        StockDetail stockDetail = stockResponse.stockDetail();
        List<StockPrice> stockPrices =
                stockPriceFetcherDataMapper.stockPriceResponsesToStockPrices(stockPriceResponses);
        List<Dividend> dividends = dividendFetcherDataMapper.dividendResponseToDividends(dividendResponse);
        return createStock(stockDetail, symbol, stockPrices, dividends);
    }

    private Stock createStock(
            StockDetail stockDetail,
            String symbol,
            List<StockPrice> stockPrices,
            List<Dividend> dividends
    ) {
        return Stock.builder()
                .name(stockDetail.productEnglishName())
                .symbol(symbol)
                .listingDate(parse(stockDetail.listingDate()))
                .stockPrices(stockPrices)
                .dividends(dividends)
                .build();
    }

    private LocalDate parse(String listingDate) {
        if (listingDate.isBlank()) {
            return null;
        }
        return DateTimeHandler.toDate(listingDate);
    }
}
