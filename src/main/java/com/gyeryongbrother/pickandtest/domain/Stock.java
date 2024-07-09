package com.gyeryongbrother.pickandtest.domain;

import java.time.LocalDate;
import java.util.List;

public class Stock {

    private Long id;
    private String name;
    private String symbol;
    private LocalDate listingDate;
    private Long marketCapitalization;
    private List<StockPrice> stockPrices;
    private List<Dividend> dividends;
    private List<IncomeStatement> incomeStatements;
}
