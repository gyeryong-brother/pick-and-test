package com.gyeryongbrother.pickandtest.stock.infrastructure.mapper;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.common.DateHandler;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.common.MoneyHandler;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto.DividendDetail;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
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
