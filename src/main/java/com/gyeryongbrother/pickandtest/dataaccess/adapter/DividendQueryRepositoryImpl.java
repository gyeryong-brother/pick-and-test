package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QDividendEntity.dividendEntity;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DividendQueryRepositoryImpl implements DividendQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final DividendDataAccessMapper dividendDataAccessMapper;

    @Override
    public List<Dividend> findAllByStockId(Long stockId) {
        List<DividendEntity> dividendEntities = queryFactory.selectFrom(dividendEntity)
                .where(dividendEntity.stock.id.eq(stockId))
                .fetch();
        return dividendEntities.stream()
                .map(dividendDataAccessMapper::dividendEntityToDividend)
                .toList();
    }
}
