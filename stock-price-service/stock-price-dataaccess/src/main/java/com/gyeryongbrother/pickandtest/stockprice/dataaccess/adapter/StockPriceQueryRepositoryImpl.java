package com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockPriceQueryRepositoryImpl implements StockPriceQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StockPrice> findAllByStockId(Long stockId) {
        List<StockPriceEntity> stockPriceEntities = queryFactory.selectFrom(stockPriceEntity)
                .where(stockPriceEntity.stockId.eq(stockId))
                .orderBy(stockPriceEntity.date.asc())
                .fetch();
        return stockPriceEntities.stream()
                .map(StockPriceEntity::toDomain)
                .toList();
    }
}
