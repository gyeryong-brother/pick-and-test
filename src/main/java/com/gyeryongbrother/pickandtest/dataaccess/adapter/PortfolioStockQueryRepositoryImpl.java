package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QPortfolioStockEntity.portfolioStockEntity;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioStockQueryRepositoryImpl implements PortfolioStockQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    @Override
    public List<PortfolioStock> findAllByPortfolioId(Long portfolioId){
        List<PortfolioStockEntity> portfolioStockEntities=
                queryFactory.selectFrom(portfolioStockEntity)
                .where(portfolioStockEntity.portfolioEntity.id.eq(portfolioId))
                .fetch();
        return portfolioStockEntities.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockEntityToPortfolioStock)
                .toList();
    }
}
