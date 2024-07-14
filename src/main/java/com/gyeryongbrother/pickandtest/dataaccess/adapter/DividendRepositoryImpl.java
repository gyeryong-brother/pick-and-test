package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendRepository;
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
