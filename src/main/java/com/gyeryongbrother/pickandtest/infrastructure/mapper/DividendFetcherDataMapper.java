package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.DateHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.MoneyHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto.DividendDetail;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto.DividendResponse;
import java.util.List;
import org.springframework.stereotype.Component;

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
                .date(DateHandler.toDate(dividendDetail.date()))
                .amount(MoneyHandler.toBigDecimal(dividendDetail.amount()))
                .build();
    }
}
