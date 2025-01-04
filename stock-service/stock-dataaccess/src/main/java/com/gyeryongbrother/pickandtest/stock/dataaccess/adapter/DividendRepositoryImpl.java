package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.DividendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DividendRepositoryImpl implements DividendRepository {

    private final DividendJpaRepository dividendJpaRepository;
    private final DividendDataAccessMapper dividendDataAccessMapper;

    @Override
    public Dividend save(Dividend dividend) {
        DividendEntity dividendEntity = dividendDataAccessMapper.dividendToDividendEntity(dividend);
        DividendEntity savedDividendEntity = dividendJpaRepository.save(dividendEntity);
        return dividendDataAccessMapper.dividendEntityToDividend(savedDividendEntity);
    }
}
