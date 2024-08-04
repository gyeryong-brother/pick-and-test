package com.gyeryongbrother.pickandtest.dataaccess.service;

import com.gyeryongbrother.pickandtest.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockDetailFixture;
import jakarta.persistence.EntityManager;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DividendQueryServiceImplTest {

    @Autowired
    public DividendQueryService dividendQueryService;
    @Autowired
    public StockRepository stockRepository;
    @Autowired
    private StockJpaRepository stockJpaRepository;
    @Autowired
    private DividendDataAccessMapper dividendDataAccessMapper;
    @Autowired
    private DividendRepository dividendRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void getAnnualDividendHistoryByName() {
        //given
        StockDetail stockDetail = StockDetailFixture.appleWithDividendsOfVariousYear();
        StockDetail savedStock = stockRepository.save(stockDetail);
        List<AnnualDividendResponse> expected = List.of(
                new AnnualDividendResponse(2020, BigDecimal.valueOf(0.45)),
                new AnnualDividendResponse(2021, BigDecimal.valueOf(0.32))
        );

        //when
        List<AnnualDividendResponse> result = dividendQueryService.getAnnualDividendsById(savedStock.getStock().getId());

        //then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
