package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.QPortfolioEntity.portfolioEntity;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioQueryRepositoryImpl implements PortfolioQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final PortfolioDataAccessMapper portfolioDataAccessMapper;

    @Override
    public List<Portfolio> findAllByMemberId(Long memberId) {
        List<PortfolioEntity> portfolioEntities = queryFactory.selectFrom(portfolioEntity)
                .where(portfolioEntity.memberId.eq(memberId))
                .fetch();
        return portfolioEntities.stream()
                .map(portfolioDataAccessMapper::portfolioEntityToPortfolio)
                .toList();
    }
}
