package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.FetchType;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.UrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchangeCode;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceFetcher {

    private final UrlProvider urlProvider;
    private final HeaderHandler headerHandler;
    private final FetcherSupport fetcherSupport;

    public StockPriceResponse fetchStockPrice(
            StockExchangeCode stockExchange,
            String symbol,
            Period period,
            LocalDate date
    ) {
        String url = urlProvider.getStockPriceEndpoint(stockExchange, symbol, period, date);
        HttpHeaders httpHeaders = headerHandler.getHeader(FetchType.STOCK_PRICE);
        ResponseEntity<StockPriceBody> responseEntity = fetcherSupport.get(url, httpHeaders, StockPriceBody.class);
        ContinuityCode continuityCode = headerHandler.parseContinuityCode(responseEntity.getHeaders());
        return new StockPriceResponse(continuityCode, responseEntity.getBody());
    }
}
