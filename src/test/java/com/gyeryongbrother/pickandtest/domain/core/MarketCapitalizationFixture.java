package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januarySecond;

import java.time.LocalDate;
import java.util.List;

public class MarketCapitalizationFixture {

    public static MarketCapitalization marketCapitalization(Long value) {
        return marketCapitalization(januaryFirst(), value);
    }

    private static MarketCapitalization marketCapitalization(LocalDate date, Long value) {
        return MarketCapitalization.builder()
                .date(date)
                .value(value)
                .build();
    }

    public static List<MarketCapitalization> marketCapitalizations() {
        return List.of(
                marketCapitalization(januaryFirst(), 100_000L),
                marketCapitalization(januarySecond(), 200_000L)
        );
    }
}
