package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DividendRepositoryImpl implements DividendRepository {

    private final DividendJpaRepository dividendJpaRepository;
    private final StockDataAccessMapper stockDataAccessMapper;

    @Override
    public Dividend save(Dividend dividend) {
        DividendEntity dividendEntity = stockDataAccessMapper.dividendToDividendEntity(dividend);
        DividendEntity savedDividendEntity = dividendJpaRepository.save(dividendEntity);
        return stockDataAccessMapper.dividendEntityToDividend(savedDividendEntity);
    }
}
