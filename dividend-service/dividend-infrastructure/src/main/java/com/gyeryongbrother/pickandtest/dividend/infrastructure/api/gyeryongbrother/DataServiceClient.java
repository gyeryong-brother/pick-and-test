package com.gyeryongbrother.pickandtest.dividend.infrastructure.api.gyeryongbrother;

import com.gyeryongbrother.pickandtest.dividend.infrastructure.api.gyeryongbrother.dto.DividendsResponse;
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

    public DividendsResponse fetchDividends(String symbol, LocalDate startDate) {
        String url = dataServiceUrlProvider.getDividendEndpoint(symbol, startDate);
        log.info("fetch dividends. url: {}", url);
        return restTemplate.getForObject(url, DividendsResponse.class);
    }
}
