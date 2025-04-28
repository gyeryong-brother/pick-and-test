package com.gyeryongbrother.pickandtest.dividend.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dividend.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dividend.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.dividend.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DividendRepositoryImpl implements DividendRepository {

    private final DividendJpaRepository dividendJpaRepository;
    private final DividendDataAccessMapper dividendDataAccessMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Dividend save(Dividend dividend) {
        DividendEntity dividendEntity = dividendDataAccessMapper.dividendToDividendEntity(dividend);
        return dividendJpaRepository.save(dividendEntity)
                .toDomain();
    }

    @Override
    public void saveAll(List<Dividend> dividends) {
        String sql = """
                    INSERT INTO dividend (stock_id, date, amount)
                    VALUES (:stockId, :date, :amount)
                """;
        int batchSize = 100;
        for (int i = 0; i < dividends.size(); i += batchSize) {
            List<Dividend> batchDividends = dividends.subList(i, Math.min(i + batchSize, dividends.size()));
            namedParameterJdbcTemplate.batchUpdate(sql, toSqlParameterSources(batchDividends));
        }
    }

    private MapSqlParameterSource[] toSqlParameterSources(List<Dividend> dividends) {
        return dividends.stream()
                .map(this::toSqlParameterSource)
                .toArray(MapSqlParameterSource[]::new);
    }

    private MapSqlParameterSource toSqlParameterSource(Dividend dividend) {
        return new MapSqlParameterSource()
                .addValue("stockId", dividend.stockId())
                .addValue("date", dividend.date())
                .addValue("amount", dividend.amount());
    }
}
