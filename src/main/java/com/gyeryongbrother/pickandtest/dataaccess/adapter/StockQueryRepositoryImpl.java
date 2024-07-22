package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockEntity.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final StockDataAccessMapper stockDataAccessMapper;

    @Override
    public StockDetail findById(Long id) {
        StockEntity stock = queryFactory.selectFrom(stockEntity)
                .join(stockEntity.stockPrices, stockPriceEntity)
                .fetchJoin()
                .where(stockEntity.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(stock)
                .map(stockDataAccessMapper::stockEntityToStockDetail)
                .orElseThrow(() -> new IllegalArgumentException("not found stock"));
    }

    @Override
    public List<StockResponse> findAllByNameOrSymbol(String keyword) {
        return queryFactory.select(stockResponse())
                .from(stockEntity)
                .where(searchCondition(keyword))
                .fetch();
    }

    private ConstructorExpression<StockResponse> stockResponse() {
        return Projections.constructor(
                StockResponse.class,
                stockEntity.id,
                stockEntity.name,
                stockEntity.symbol
        );
    }

    private BooleanExpression searchCondition(String keyword) {
        BooleanExpression nameCondition = stockEntity.name.startsWithIgnoreCase(keyword);
        BooleanExpression symbolCondition = stockEntity.symbol.startsWithIgnoreCase(keyword);
        return nameCondition.or(symbolCondition);
    }
}
