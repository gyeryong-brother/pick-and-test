package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.common;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DateHandler {

    private static final String DATE_PATTERN = "MM/dd/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDate toDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
