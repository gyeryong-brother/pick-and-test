package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DateTimeHandler {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDateTime toDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    public static LocalDate toDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static String toDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}
