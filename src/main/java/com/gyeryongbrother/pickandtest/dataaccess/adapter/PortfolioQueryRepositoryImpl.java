package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.QPortfolioEntity.portfolioEntity;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioQueryRepositoryImpl implements
        com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final PortfolioDataAccessMapper portfolioDataAccessMapper;

    @Override
    public List<Portfolio> findAllByMemberId(Long memberId) {
        List<PortfolioEntity> portfolioEntities =
                queryFactory.selectFrom(portfolioEntity)
                        .where(portfolioEntity.memberEntity.id.eq(memberId))
                        .fetch();
        return portfolioEntities.stream()
                .map(portfolioDataAccessMapper::portfolioEntityToPortfolio)
                .toList();
    }
}
