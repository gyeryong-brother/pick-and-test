package com.gyeryongbrother.pickandtest.dividend.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendFetcher;
import com.gyeryongbrother.pickandtest.dividend.infrastructure.api.gyeryongbrother.DataServiceClient;
import com.gyeryongbrother.pickandtest.dividend.infrastructure.api.gyeryongbrother.dto.DividendsResponse;
import com.gyeryongbrother.pickandtest.dividend.infrastructure.exception.DividendInfrastructureException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DividendFetcherImpl implements DividendFetcher {

    private final DataServiceClient dataServiceClient;

    @Override
    public List<Dividend> fetchDividends(Stock stock, LocalDate startDate) {
        try {
            return fetchDividendsById(stock, startDate);
        } catch (DividendInfrastructureException e) {
            log.error("fetch failed. message: {}", e.getMessage());
            return List.of();
        }
    }

    private List<Dividend> fetchDividendsById(Stock stock, LocalDate startDate) {
        log.info("fetch dividends. stock id: {}, symbol: {}", stock.id(), stock.symbol());
        DividendsResponse dividendsResponse = dataServiceClient.fetchDividends(stock.symbol(), startDate);
        return dividendsResponse.dividends().stream()
                .map(it -> it.toDomain(stock.id()))
                .toList();
    }
}
