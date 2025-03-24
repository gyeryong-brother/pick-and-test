package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import java.time.LocalDate;

public class DateProviderImpl implements DateProvider {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
