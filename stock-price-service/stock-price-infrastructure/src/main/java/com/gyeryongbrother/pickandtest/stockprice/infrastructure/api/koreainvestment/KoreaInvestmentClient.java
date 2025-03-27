package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.Period.DAY;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.common.FetcherSupport;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.FetchType;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.KoreaInvestmentUrlProvider;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.StockExchangeCode;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.ContinuityCode;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KoreaInvestmentClient {

    private final KoreaInvestmentUrlProvider koreaInvestmentUrlProvider;
    private final HeaderHandler headerHandler;
    private final FetcherSupport fetcherSupport;

    public StockPriceResponse fetchOneHundredStockPricesBeforeDate(
            StockExchange stockExchange,
            String symbol,
            LocalDate date
    ) {
        String url = koreaInvestmentUrlProvider.getStockPriceEndpoint(StockExchangeCode.from(stockExchange), symbol,
                DAY, date);
        HttpHeaders httpHeaders = headerHandler.getHeader(FetchType.STOCK_PRICE);
        ResponseEntity<StockPriceBody> responseEntity = fetcherSupport.get(url, httpHeaders, StockPriceBody.class);
        ContinuityCode continuityCode = headerHandler.parseContinuityCode(responseEntity.getHeaders());
        return new StockPriceResponse(continuityCode, responseEntity.getBody());
    }
}
