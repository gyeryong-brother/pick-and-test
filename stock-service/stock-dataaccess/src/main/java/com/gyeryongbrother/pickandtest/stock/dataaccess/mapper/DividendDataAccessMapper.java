package com.gyeryongbrother.pickandtest.stock.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import org.springframework.stereotype.Component;

@Component
public class DividendDataAccessMapper {

    public DividendEntity dividendToDividendEntity(Dividend dividend) {
        return DividendEntity.builder()
                .id(dividend.getId())
                .date(dividend.getDate())
                .amount(dividend.getAmount())
                .stockId(dividend.getStockId())
                .build();
    }

    public Dividend dividendEntityToDividend(DividendEntity dividendEntity) {
        return Dividend.builder()
                .id(dividendEntity.getId())
                .stockId(dividendEntity.getStockId())
                .date(dividendEntity.getDate())
                .amount(dividendEntity.getAmount())
                .build();
    }
}
