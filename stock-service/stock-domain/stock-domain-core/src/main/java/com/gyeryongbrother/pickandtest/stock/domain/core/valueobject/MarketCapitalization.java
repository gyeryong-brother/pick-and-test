package com.gyeryongbrother.pickandtest.stock.domain.core.valueobject;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MarketCapitalization {

    private final LocalDate date;
    private final Long value;
}
