package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockEntity.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final StockDataAccessMapper stockDataAccessMapper;

    @Override
    public StockDetail findById(Long id) {
        StockEntity stock = queryFactory.selectFrom(stockEntity)
                .leftJoin(stockEntity.stockPrices, stockPriceEntity)
                .fetchJoin()
                .where(stockEntity.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(stock)
                .map(stockDataAccessMapper::stockEntityToStockDetail)
                .orElseThrow(() -> new IllegalArgumentException("not found stock"));
    }

    @Override
    public List<Stock> findAllByNameOrSymbol(String keyword) {
        List<StockEntity> stockEntities = queryFactory.selectFrom(stockEntity)
                .where(searchCondition(keyword))
                .fetch();
        return stockEntities.stream()
                .map(stockDataAccessMapper::stockEntityToStock)
                .toList();
    }

    private BooleanExpression searchCondition(String keyword) {
        BooleanExpression nameCondition = stockEntity.name.startsWithIgnoreCase(keyword);
        BooleanExpression symbolCondition = stockEntity.symbol.startsWithIgnoreCase(keyword);
        return nameCondition.or(symbolCondition);
    }
}
