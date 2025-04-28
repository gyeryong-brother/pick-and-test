package com.gyeryongbrother.pickandtest.dividend.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dividend.dataaccess.entity.QDividendEntity.dividendEntity;

import com.gyeryongbrother.pickandtest.dividend.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject.Dividends;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DividendQueryRepositoryImpl implements DividendQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Dividends findDividendsByStockId(Long stockId) {
        List<DividendEntity> dividendEntities = queryFactory.selectFrom(dividendEntity)
                .where(dividendEntity.stockId.eq(stockId))
                .fetch();
        List<Dividend> dividends = dividendEntities.stream()
                .map(DividendEntity::toDomain)
                .toList();
        return Dividends.from(dividends);
    }

    @Override
    public LocalDate findLastDateOfDividendsByStockId(Long stockId) {
        return null;
    }
}
