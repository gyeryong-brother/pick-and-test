package com.gyeryongbrother.pickandtest.dividend.domain.core.entity;

public class Stock {

    private final Long id;
    private final String symbol;

    public Stock(Long id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public Long id() {
        return id;
    }

    public String symbol() {
        return symbol;
    }
}
