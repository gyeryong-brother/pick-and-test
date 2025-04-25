package com.gyeryongbrother.pickandtest.dividend.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dividend.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dividend.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.dividend.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendRepository;
import java.util.List;
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
        return dividendJpaRepository.save(dividendEntity)
                .toDomain();
    }

    @Override
    public void saveAll(List<Dividend> dividends) {

    }
}
