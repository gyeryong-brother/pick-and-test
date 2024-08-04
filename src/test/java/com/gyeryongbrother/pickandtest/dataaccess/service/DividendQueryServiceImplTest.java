package com.gyeryongbrother.pickandtest.dataaccess.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
        Stock stock = StockFixture.apple();
        Stock savedStock = stockRepository.save(stock);
        /*LocalDate date1 = LocalDate.of(2020, 3, 1);
        LocalDate date2 = LocalDate.of(2020, 6, 1);
        LocalDate date3 = LocalDate.of(2021, 4, 1);
        BigDecimal amount1 = BigDecimal.valueOf(0.22);
        BigDecimal amount2 = BigDecimal.valueOf(0.23);
        BigDecimal amount3 = BigDecimal.valueOf(0.32);
        List<Dividend> dividends = List.of(
                dividend(savedStock, date1, amount1),
                dividend(savedStock, date2, amount2),
                dividend(savedStock, date3, amount3)
        );*/
        //dividends.forEach(dividendRepository::save);
        List<AnnualDividendResponse> expected = List.of(
                new AnnualDividendResponse(2020, BigDecimal.valueOf(0.45)),
                new AnnualDividendResponse(2021, BigDecimal.valueOf(0.32))
        );

        //when
        List<AnnualDividendResponse> result = dividendQueryService.getAnnualDividendsById(savedStock.getId());

        //then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    private Dividend dividend(Stock stock, LocalDate date, BigDecimal amount) {
        return Dividend.builder()
                .stockId(stock.getId())
                .date(date)
                .amount(amount)
                .build();
    }
}
