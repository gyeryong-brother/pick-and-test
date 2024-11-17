package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januarySecond;

import java.util.List;

public class MarketCapitalizationResponseFixture {

    public static List<MarketCapitalizationResponse> marketCapitalizationResponses() {
        return List.of(
                new MarketCapitalizationResponse(januaryFirst(), 100_000L),
                new MarketCapitalizationResponse(januarySecond(), 200_000L)
        );
    }
}
