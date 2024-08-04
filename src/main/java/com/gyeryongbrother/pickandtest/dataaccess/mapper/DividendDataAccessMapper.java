package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DividendDataAccessMapper {

    public DividendEntity dividendToDividendEntity(Dividend dividend) {
        StockEntity stockEntity = StockEntity.builder()
                .id(dividend.getStockId())
                .build();
        return DividendEntity.builder()
                .id(dividend.getId())
                .date(dividend.getDate())
                .amount(dividend.getAmount())
                .stock(stockEntity)
                .build();
    }

    public Dividend dividendEntityToDividend(DividendEntity dividendEntity) {
        return Dividend.builder()
                .id(dividendEntity.getId())
                .stockId(dividendEntity.getStock().getId())
                .date(dividendEntity.getDate())
                .amount(dividendEntity.getAmount())
                .build();
    }

    List<Dividend> dividendEntitiesToDividends(List<DividendEntity> dividendEntities) {
        return dividendEntities.stream()
                .map(this::dividendEntityToDividend)
                .toList();
    }
}
