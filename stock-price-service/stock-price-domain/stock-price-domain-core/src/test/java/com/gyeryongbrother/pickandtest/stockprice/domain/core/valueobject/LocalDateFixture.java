package com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject;

import java.time.LocalDate;

public class LocalDateFixture {

    public static LocalDate januaryFirst() {
        return LocalDate.of(2024, 1, 1);
    }

    public static LocalDate januarySecond() {
        return LocalDate.of(2024, 1, 2);
    }

    public static LocalDate twentyTwenty() {
        return LocalDate.of(2020, 1, 1);
    }

    public static LocalDate twentyTwentyFour() {
        return LocalDate.of(2024, 1, 1);
    }
}
