package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange.AMEX;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange.NYSE;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.NOT_SUPPORTED_STOCK_EXCHANGE;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StockExchangeCode {

    NASDAQ_CODE("512", "NAS"),
    NYSE_CODE("513", "NYS"),
    AMEX_CODE("529", "AMS"),
    ;

    private final String productTypeCode;
    private final String exchangeCode;

    public static StockExchangeCode from(StockExchange stockExchange) {
        if (stockExchange == NASDAQ) {
            return NASDAQ_CODE;
        }
        if (stockExchange == NYSE) {
            return NYSE_CODE;
        }
        if (stockExchange == AMEX) {
            return AMEX_CODE;
        }
        throw new StockPriceInfrastructureException(NOT_SUPPORTED_STOCK_EXCHANGE);
    }
}
