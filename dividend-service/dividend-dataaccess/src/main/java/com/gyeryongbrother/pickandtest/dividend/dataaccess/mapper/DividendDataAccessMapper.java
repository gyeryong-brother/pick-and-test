package com.gyeryongbrother.pickandtest.dividend.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dividend.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import org.springframework.stereotype.Component;

@Component
public class DividendDataAccessMapper {

    public DividendEntity dividendToDividendEntity(Dividend dividend) {
        return DividendEntity.builder()
                .id(dividend.id())
                .stockId(dividend.stockId())
                .date(dividend.date())
                .amount(dividend.amount())
                .build();
    }
}
