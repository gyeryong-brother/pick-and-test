package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceDetail;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockPriceFetcherDataMapper {

    public List<StockPrice> stockPriceResponsesToStockPrices(List<StockPriceResponse> stockPriceResponses) {
        return stockPriceResponses.stream()
                .map(StockPriceResponse::stockPriceBody)
                .map(StockPriceBody::stockPriceDetails)
                .flatMap(List::stream)
                .map(this::stockPriceDetailToStockPrice)
                .toList();
    }

    private StockPrice stockPriceDetailToStockPrice(StockPriceDetail stockPriceDetail) {
        return StockPrice.builder()
                .date(DateTimeHandler.toDate(stockPriceDetail.date()))
                .price(new BigDecimal(stockPriceDetail.price()))
                .build();
    }
}
