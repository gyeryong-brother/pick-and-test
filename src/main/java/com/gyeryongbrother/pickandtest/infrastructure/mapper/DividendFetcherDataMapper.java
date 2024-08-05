package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendDetail;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DividendFetcherDataMapper {

    public List<Dividend> dividendResponseToDividends(DividendResponse dividendResponse) {
        List<DividendDetail> dividendDetails = dividendResponse.dividendDetails();
        return dividendDetails.stream()
                .map(this::dividendDetailToDividend)
                .toList();
    }

    private Dividend dividendDetailToDividend(DividendDetail dividendDetail) {
        return Dividend.builder()
                .date(LocalDate.parse(dividendDetail.date()))
                .amount(new BigDecimal(dividendDetail.amount()))
                .build();
    }
}
