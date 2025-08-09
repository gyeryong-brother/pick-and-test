package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataServiceClient {

    private final DataServiceUrlProvider dataServiceUrlProvider;
    private final RestTemplate restTemplate;

    public Map<String, List<StockPriceResponse>> fetchStockPrices(List<String> symbols, LocalDate startDate) {
        String url = dataServiceUrlProvider.getStockPriceEndpoint(symbols, startDate);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, List<StockPriceResponse>>>() {
                }
        ).getBody();
    }

    public Map<String, List<StockPriceResponse>> fetchStockMinutePrices(List<String> symbols, LocalDate startDate) {
        String url = dataServiceUrlProvider.getStockMinutePriceEndpoint(symbols, startDate);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, List<StockPriceResponse>>>() {
                }
        ).getBody();
    }
}
