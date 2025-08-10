package com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter.command;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockMinutePriceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockMinutePriceRepositoryImpl implements StockMinutePriceRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveAll(List<StockMinutePrice> stockMinutePrices) {
        String sql = """
                    INSERT INTO stock_minute_price (stock_id, date, price)
                    VALUES (:stockId, :date, :price)
                """;
        int batchSize = 100;
        for (int i = 0; i < stockMinutePrices.size(); i += batchSize) {
            List<StockMinutePrice> batchStockPrices = stockMinutePrices.subList(i,
                    Math.min(i + batchSize, stockMinutePrices.size()));
            namedParameterJdbcTemplate.batchUpdate(sql, toSqlParameterSources(batchStockPrices));
        }
    }

    private MapSqlParameterSource[] toSqlParameterSources(List<StockMinutePrice> stockMinutePrices) {
        return stockMinutePrices.stream()
                .map(this::toSqlParameterSource)
                .toArray(MapSqlParameterSource[]::new);
    }

    private MapSqlParameterSource toSqlParameterSource(StockMinutePrice stockMinutePrice) {
        return new MapSqlParameterSource()
                .addValue("stockId", stockMinutePrice.stockId())
                .addValue("dateTime", stockMinutePrice.dateTime())
                .addValue("price", stockMinutePrice.price());
    }
}
