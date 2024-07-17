package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockDetail;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockFetcherDataMapper {

    private final StockPriceFetcherDataMapper stockPriceFetcherDataMapper;

    public Stock stockResponseToStock(
            StockResponse stockResponse,
            String symbol,
            List<StockPriceResponse> stockPriceResponses
    ) {
        StockDetail stockDetail = stockResponse.stockDetail();
        List<StockPrice> stockPrices =
                stockPriceFetcherDataMapper.stockPriceResponsesToStockPrices(stockPriceResponses);
        return Stock.builder()
                .name(stockDetail.productEnglishName())
                .symbol(symbol)
                .listingDate(parse(stockDetail.listingDate()))
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
