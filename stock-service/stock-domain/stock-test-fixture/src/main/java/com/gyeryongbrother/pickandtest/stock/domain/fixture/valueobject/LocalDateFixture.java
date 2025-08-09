package com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class LocalDateFixture {

    public static LocalDate januaryFirst() {
        return LocalDate.of(2024, 1, 1);
    }

    public static LocalDate januarySecond() {
        return LocalDate.of(2024, 1, 2);
    }

    public static LocalDate januaryThird() {
        return LocalDate.of(2024, 1, 3);
    }

    public static LocalDate twentyTwenty() {
        return LocalDate.of(2020, 1, 1);
    }
}
