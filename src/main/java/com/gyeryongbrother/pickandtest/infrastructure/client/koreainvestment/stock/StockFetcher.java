package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.FetchType;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.KoreaInvestmentUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.StockExchangeCode;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockFetcher {

    private final KoreaInvestmentUrlProvider koreaInvestmentUrlProvider;
    private final HeaderHandler headerHandler;
    private final FetcherSupport fetcherSupport;

    public StockResponse fetchStock(StockExchangeCode stockExchangeCode, String symbol) {
        String url = koreaInvestmentUrlProvider.getStockEndpoint(stockExchangeCode, symbol);
        HttpHeaders httpHeaders = headerHandler.getHeader(FetchType.STOCK);
        return fetcherSupport.get(url, httpHeaders, StockResponse.class)
                .getBody();
    }
}
