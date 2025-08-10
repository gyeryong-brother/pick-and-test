package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter.query;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QStockDetailEntity.stockDetailEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QStockEntity.stockEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.exception.StockDataExceptionType.STOCK_NOT_FOUND;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.exception.StockDataException;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
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
@Transactional(readOnly = true, transactionManager = "slaveTransactionManager")
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final StockDataAccessMapper stockDataAccessMapper;

    private StockEntity selectStockEntity(Long id) {
        return queryFactory.selectFrom(stockEntity)
                .leftJoin(stockEntity.stockDetail, stockDetailEntity)
                .fetchJoin()
                .where(stockEntity.id.eq(id))
                .fetchOne();
    }

    @Override
    public Stock getById(Long id) {
        return Optional.ofNullable(selectStockEntity(id))
                .map(stockDataAccessMapper::stockEntityToStock)
                .orElseThrow(() -> new StockDataException(STOCK_NOT_FOUND));
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

    @Override
    public List<String> findAllSymbolsByStockExchange(StockExchange stockExchange) {
        return queryFactory.select(stockEntity.symbol)
                .from(stockEntity)
                .where(stockEntity.stockExchange.eq(stockExchange))
                .fetch();
    }

    @Override
    public List<Stock> findAll() {
        List<StockEntity> stockEntities = queryFactory.selectFrom(stockEntity)
                .fetch();
        return stockEntities.stream()
                .map(stockDataAccessMapper::stockEntityToStock)
                .toList();
    }
}
