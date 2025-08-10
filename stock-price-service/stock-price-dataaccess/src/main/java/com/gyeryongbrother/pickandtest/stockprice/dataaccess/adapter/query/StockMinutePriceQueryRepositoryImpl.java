package com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter.query;

import static com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.QStockMinutePriceEntity.stockMinutePriceEntity;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.StockMinutePriceEntity;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockMinutePriceQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockMinutePriceQueryRepositoryImpl implements StockMinutePriceQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public LocalDate findLastDateOfStockMinutePricesByStockId(Long stockId) {
        StockMinutePriceEntity stockMinutePrice = queryFactory.selectFrom(stockMinutePriceEntity)
                .where(stockMinutePriceEntity.stockId.eq(stockId))
                .orderBy(stockMinutePriceEntity.dateTime.desc())
                .limit(1)
                .fetchOne();
        if (stockMinutePrice == null) {
            return null;
        }
        return stockMinutePrice.dateTime().toLocalDate();
    }
}
