package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QFavoriteStockEntity.favoriteStockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockEntity.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.QStockPriceEntity.stockPriceEntity;

import com.gyeryongbrother.pickandtest.dataaccess.entity.FavoriteStockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.FavoriteStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteStockQueryRepositoryImpl implements FavoriteStockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final FavoriteStockDataAccessMapper favoriteStockDataAccessMapper;

    @Override
    public List<FavoriteStock> findAllByMemberId(Long memberId) {
        List<FavoriteStockEntity> favoriteStockEntities = queryFactory.selectFrom(favoriteStockEntity)
                .join(favoriteStockEntity.stock, stockEntity)
                .fetchJoin()
                .leftJoin(stockEntity.stockPrices, stockPriceEntity)
                .fetchJoin()
                .where(favoriteStockEntity.memberId.eq(memberId))
                .fetch();
        return favoriteStockDataAccessMapper.favoriteStockEntitiesToFavoriteStocks(favoriteStockEntities);
    }
}
