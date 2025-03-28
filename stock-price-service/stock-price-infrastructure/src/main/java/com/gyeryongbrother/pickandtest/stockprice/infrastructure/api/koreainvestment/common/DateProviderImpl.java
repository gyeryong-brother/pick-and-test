package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class DateProviderImpl implements DateProvider {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
