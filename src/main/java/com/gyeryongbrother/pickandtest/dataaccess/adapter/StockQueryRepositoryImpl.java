package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockEntity.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.domain.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockQueryRepositoryImpl implements StockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final StockDataAccessMapper stockDataAccessMapper;

    @Override
    public Stock findById(Long id) {
        StockEntity stock = queryFactory.selectFrom(stockEntity)
                .join(stockEntity.stockPrices, stockPriceEntity)
                .fetchJoin()
                .where(stockEntity.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(stock)
                .map(stockDataAccessMapper::stockEntityToStock)
                .orElseThrow(() -> new IllegalArgumentException("not found stock"));
    }
}
