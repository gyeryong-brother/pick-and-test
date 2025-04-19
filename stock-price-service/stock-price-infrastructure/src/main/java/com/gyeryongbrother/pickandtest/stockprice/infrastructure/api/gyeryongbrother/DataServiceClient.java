package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.common.FetcherSupport;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockPricesResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataServiceClient {

    private final DataServiceUrlProvider dataServiceUrlProvider;
    private final FetcherSupport fetcherSupport;

    public StockPricesResponse fetchStockPrices(String symbol, LocalDate startDate) {
        String url = dataServiceUrlProvider.getStockPriceEndpoint(symbol, startDate);
        log.info("fetch stock prices. url: {}", url);
        return fetcherSupport.get(url, StockPricesResponse.class);
    }
}
