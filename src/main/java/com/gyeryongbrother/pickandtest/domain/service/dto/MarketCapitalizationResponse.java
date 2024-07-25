package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.MarketCapitalization;
import java.time.LocalDate;

public record MarketCapitalizationResponse(
        LocalDate date,
        Long value
) {

    public static MarketCapitalizationResponse from(MarketCapitalization marketCapitalization) {
        return new MarketCapitalizationResponse(
                marketCapitalization.getDate(),
                marketCapitalization.getValue()
        );
    }
}
