package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.MarketCapitalization;
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
