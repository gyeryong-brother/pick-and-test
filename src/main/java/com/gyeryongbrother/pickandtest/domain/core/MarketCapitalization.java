package com.gyeryongbrother.pickandtest.domain.core;

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
