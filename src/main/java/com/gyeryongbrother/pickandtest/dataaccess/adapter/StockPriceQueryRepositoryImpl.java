package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockPriceQueryRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockPriceQueryRepositoryImpl implements StockPriceQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StockPriceResponse> findAllByStockId(Long stockId) {
        return queryFactory.select(stockPriceResponse())
                .from(stockPriceEntity)
                .where(stockPriceEntity.stock.id.eq(stockId))
                .orderBy(stockPriceEntity.date.asc())
                .fetch();
    }

    private ConstructorExpression<StockPriceResponse> stockPriceResponse() {
        return Projections.constructor(
                StockPriceResponse.class,
                stockPriceEntity.date,
                stockPriceEntity.price
        );
    }
}
