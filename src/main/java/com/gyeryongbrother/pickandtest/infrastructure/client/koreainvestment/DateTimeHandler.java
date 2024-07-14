package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class DateTimeHandler {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public LocalDateTime parse(String accessTokenExpired) {
        return LocalDateTime.parse(accessTokenExpired, DATE_TIME_FORMATTER);
    }

    public String toQueryParam(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}
