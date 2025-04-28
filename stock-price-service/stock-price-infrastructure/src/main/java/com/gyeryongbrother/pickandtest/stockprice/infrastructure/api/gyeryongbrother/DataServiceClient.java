package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockPricesResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataServiceClient {

    private final DataServiceUrlProvider dataServiceUrlProvider;
    private final RestTemplate restTemplate;

    public StockPricesResponse fetchStockPrices(String symbol, LocalDate startDate) {
        String url = dataServiceUrlProvider.getStockPriceEndpoint(symbol, startDate);
        log.info("fetch stock prices. url: {}", url);
        return restTemplate.getForObject(url, StockPricesResponse.class);
    }
}
