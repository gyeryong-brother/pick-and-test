package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.QPortfolioEntity.portfolioEntity;
import static com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.QPortfolioStockEntity.portfolioStockEntity;
import static com.gyeryongbrother.pickandtest.portfolio.dataaccess.exception.PortfolioExceptionType.PORTFOLIO_NOT_FOUND;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.exception.PortfolioException;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioQueryRepositoryImpl implements PortfolioQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final PortfolioDataAccessMapper portfolioDataAccessMapper;
    private final PortfolioJpaRepository portfolioJpaRepository;

    @Override
    public List<Portfolio> findAllByMemberId(Long memberId) {
        List<PortfolioEntity> portfolioEntities = queryFactory.selectFrom(portfolioEntity)
                .where(portfolioEntity.memberId.eq(memberId))
                .fetch();
        return portfolioEntities.stream()
                .map(portfolioDataAccessMapper::portfolioEntityToPortfolio)
                .toList();
    }

    @Override
    public Portfolio findById(Long portfolioId) {
        PortfolioEntity fetchedPortfolioEntity = queryFactory.selectFrom(portfolioEntity)
                .leftJoin(portfolioEntity.portfolioStockEntities, portfolioStockEntity)
                .fetchJoin()
                .where(portfolioEntity.id.eq(portfolioId))
                .fetchOne();
        Optional<PortfolioEntity> portfolioEntityOptional = Optional.ofNullable(fetchedPortfolioEntity);
        return portfolioEntityOptional.map(portfolioDataAccessMapper::portfolioEntityToPortfolio)
                .orElseThrow(() -> new PortfolioException(PORTFOLIO_NOT_FOUND));
    }

    @Override
    public Portfolio findByIdWithPessimisticLock(Long portfolioId) {
        return portfolioJpaRepository.findByIdWithPessimisticLock(portfolioId)
                .map(portfolioDataAccessMapper::portfolioEntityToPortfolio)
                .orElseThrow(() -> new PortfolioException(PORTFOLIO_NOT_FOUND));
    }
}
