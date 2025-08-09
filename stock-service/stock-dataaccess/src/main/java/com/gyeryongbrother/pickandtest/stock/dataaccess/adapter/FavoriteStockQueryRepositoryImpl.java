package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QFavoriteStockEntity.favoriteStockEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QStockDetailEntity.stockDetailEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.QStockEntity.stockEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.exception.StockDataExceptionType.FAVORITE_STOCK_NOT_FOUND;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.FavoriteStockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.exception.StockDataException;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.FavoriteStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
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
                .leftJoin(stockEntity.stockDetail, stockDetailEntity)
                .fetchJoin()
                .where(favoriteStockEntity.memberId.eq(memberId))
                .fetch();
        return favoriteStockDataAccessMapper.favoriteStockEntitiesToFavoriteStocks(favoriteStockEntities);
    }

    @Override
    public FavoriteStock getById(Long id) {
        FavoriteStockEntity fetchedFavoriteStockEntity = queryFactory.selectFrom(favoriteStockEntity)
                .where(favoriteStockEntity.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(fetchedFavoriteStockEntity)
                .map(favoriteStockDataAccessMapper::favoriteStockEntityToFavoriteStock)
                .orElseThrow(() -> new StockDataException(FAVORITE_STOCK_NOT_FOUND));
    }

    @Override
    public Optional<FavoriteStock> findByStockIdAndMemberId(Long stockId, Long memberId) {
        FavoriteStockEntity fetchedFavoriteStockEntity = queryFactory.selectFrom(favoriteStockEntity)
                .where(favoriteStockEntity.stock.id.eq(stockId), favoriteStockEntity.memberId.eq(memberId))
                .fetchOne();
        return Optional.ofNullable(fetchedFavoriteStockEntity)
                .map(favoriteStockDataAccessMapper::favoriteStockEntityToFavoriteStock);
    }

    @Override
    public FavoriteStock getByStockIdAndMemberId(Long stockId, Long memberId) {
        return findByStockIdAndMemberId(stockId, memberId)
                .orElseThrow(() -> new StockDataException(FAVORITE_STOCK_NOT_FOUND));
    }
}
