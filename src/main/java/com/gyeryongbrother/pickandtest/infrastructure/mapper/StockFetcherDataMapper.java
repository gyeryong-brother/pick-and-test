package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockInformation;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockFetcherDataMapper {

    private final StockPriceFetcherDataMapper stockPriceFetcherDataMapper;

    public Stock fetchStockResponseToStock(
            FetchStockResponse fetchStockResponse,
            String symbol,
            List<FetchStockPriceResponse> fetchStockPriceResponses
    ) {
        StockInformation stockInformation = fetchStockResponse.stockInformation();
        List<StockPrice> stockPrices =
                stockPriceFetcherDataMapper.fetchStockPriceResponsesToStockPrices(fetchStockPriceResponses);
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
}
