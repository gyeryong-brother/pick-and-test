package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QStockEntity.stockEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockWithPrices;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final StockDataAccessMapper stockDataAccessMapper;

    @Override
    public StockWithPrices findStockWithPricesById(Long id) {
        return Optional.ofNullable(selectStockEntity(id))
                .map(stockDataAccessMapper::stockEntityToStockWithPrices)
                .orElseThrow(() -> new IllegalArgumentException("not found stock"));
    }

    private StockEntity selectStockEntity(Long id) {
        return queryFactory.selectFrom(stockEntity)
                .leftJoin(stockEntity.stockPrices, stockPriceEntity)
                .fetchJoin()
                .where(stockEntity.id.eq(id))
                .fetchOne();
    }

    @Override
    public StockDetail findById(Long id) {
        return Optional.ofNullable(selectStockEntity(id))
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
