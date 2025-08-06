package com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.mapper.StockPriceDataAccessMapper;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockPriceRepositoryImpl implements StockPriceRepository {

    private final StockPriceJpaRepository stockPriceJpaRepository;
    private final StockPriceDataAccessMapper stockPriceDataAccessMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public StockPrice save(StockPrice stockPrice) {
        StockPriceEntity stockPriceEntity = stockPriceDataAccessMapper.stockPriceToStockPriceEntity(stockPrice);
        StockPriceEntity savedStockPriceEntity = stockPriceJpaRepository.save(stockPriceEntity);
        return savedStockPriceEntity.toDomain();
    }

    @Override
    public void saveAll(List<StockPrice> stockPrices) {
        String sql = """
                    INSERT INTO stock_price (stock_id, date, price)
                    VALUES (:stockId, :date, :price)
                """;
        int batchSize = 100;
        for (int i = 0; i < stockPrices.size(); i += batchSize) {
            List<StockPrice> batchStockPrices = stockPrices.subList(i, Math.min(i + batchSize, stockPrices.size()));
            namedParameterJdbcTemplate.batchUpdate(sql, toSqlParameterSources(batchStockPrices));
        }
    }

    private MapSqlParameterSource[] toSqlParameterSources(final List<StockPrice> stockPrices) {
        return stockPrices.stream()
                .map(this::toSqlParameterSource)
                .toArray(MapSqlParameterSource[]::new);
    }

    private MapSqlParameterSource toSqlParameterSource(final StockPrice stockPrice) {
        return new MapSqlParameterSource()
                .addValue("stockId", stockPrice.stockId())
                .addValue("date", stockPrice.date())
                .addValue("price", stockPrice.price());
    }

    @Override
    public void saveAllSeperately(List<StockPrice> stockPrices) {
        List<StockPriceEntity> stockPriceEntities = stockPriceDataAccessMapper.stockPricesToStockPriceEntities(stockPrices);
        stockPriceJpaRepository.saveAll(stockPriceEntities);
    }
}
