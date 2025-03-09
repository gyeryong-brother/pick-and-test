package com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject;

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
}
