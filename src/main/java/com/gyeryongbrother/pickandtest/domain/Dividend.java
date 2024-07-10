package com.gyeryongbrother.pickandtest.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class Dividend {

    private final Long id;
    private final Long stockId;
    private final LocalDate date;
    private final BigDecimal amount;
}
