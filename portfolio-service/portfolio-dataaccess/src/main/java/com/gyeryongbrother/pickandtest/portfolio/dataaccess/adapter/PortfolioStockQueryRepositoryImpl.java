package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.QPortfolioStockEntity.portfolioStockEntity;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioStockQueryRepositoryImpl implements PortfolioStockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    @Override
    public List<PortfolioStock> findAllByPortfolioId(Long portfolioId) {
        List<PortfolioStockEntity> portfolioStockEntities = queryFactory.selectFrom(portfolioStockEntity)
                .where(portfolioStockEntity.portfolioEntity.id.eq(portfolioId))
                .fetch();
        return portfolioStockEntities.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockEntityToPortfolioStock)
                .toList();
    }
}
