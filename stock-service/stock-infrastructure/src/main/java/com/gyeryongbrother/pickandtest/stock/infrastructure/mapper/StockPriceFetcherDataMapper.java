package com.gyeryongbrother.pickandtest.stock.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.DateTimeHandler;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceDetail;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockPriceFetcherDataMapper {

    public List<StockPrice> stockPriceResponsesToStockPrices(
            List<StockPriceResponse> stockPriceResponses) {
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
