package com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class BigDecimalFixture {

    public static BigDecimal oneHundred() {
        return BigDecimal.valueOf(100);
    }

    public static BigDecimal twoHundred() {
        return BigDecimal.valueOf(200);
    }

    public static BigDecimal threeHundred() {
        return BigDecimal.valueOf(300);
    }

    public static BigDecimal oneThousand() {
        return BigDecimal.valueOf(1000);
    }
}
