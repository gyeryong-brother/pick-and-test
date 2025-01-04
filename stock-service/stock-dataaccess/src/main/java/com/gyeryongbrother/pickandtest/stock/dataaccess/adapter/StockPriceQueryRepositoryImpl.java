package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.StockPriceDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockPriceQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockPriceQueryRepositoryImpl implements StockPriceQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final StockPriceDataAccessMapper stockPriceDataAccessMapper;

    @Override
    public List<StockPrice> findAllByStockId(Long stockId) {
        List<StockPriceEntity> stockPriceEntities = queryFactory.selectFrom(stockPriceEntity)
                .where(stockPriceEntity.stock.id.eq(stockId))
                .orderBy(stockPriceEntity.date.asc())
                .fetch();
        return stockPriceDataAccessMapper.stockPriceEntitiesToStockPrices(stockPriceEntities);
    }
}
