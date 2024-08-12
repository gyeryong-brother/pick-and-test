package com.gyeryongbrother.pickandtest.domain.service.dto;

import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januarySecond;

import java.util.List;

public class MarketCapitalizationResponseFixture {

    public static List<MarketCapitalizationResponse> marketCapitalizationResponses() {
        return List.of(
                new MarketCapitalizationResponse(januaryFirst(), 100_000L),
                new MarketCapitalizationResponse(januarySecond(), 200_000L)
        );
    }
}
