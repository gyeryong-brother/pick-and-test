package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceInformation;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockPriceFetcherDataMapper {

    public List<StockPrice> fetchStockPriceResponsesToStockPrices(
            List<FetchStockPriceResponse> fetchStockPriceResponses
    ) {
        return fetchStockPriceResponses.stream()
                .map(FetchStockPriceResponse::stockPriceBody)
                .map(StockPriceBody::stockPriceInformation)
                .flatMap(List::stream)
                .map(this::stockPriceInformationToStockPrice)
                .toList();
    }

    private StockPrice stockPriceInformationToStockPrice(StockPriceInformation stockPriceInformation) {
        return StockPrice.builder()
                .date(DateTimeHandler.toDate(stockPriceInformation.date()))
                .price(new BigDecimal(stockPriceInformation.price()))
                .build();
    }
}
