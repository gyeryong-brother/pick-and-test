package com.gyeryongbrother.pickandtest.dataaccess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatPredicate;
import static org.assertj.core.api.InstanceOfAssertFactories.atomicLongFieldUpdater;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.DividendDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.GetHistory;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.AnnualDividend;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.parser.Entity;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetHistoryImplTest {

    @Autowired
    public GetHistory getHistory;
    @Autowired
    public StockRepository stockRepository;
    @Autowired
    private StockJpaRepository stockJpaRepository;
    @Autowired
    private DividendDataAccessMapper dividendDataAccessMapper;

    @Test
    void getAnnualDividendHistoryByName() {
        //given
        LocalDate date1 = LocalDate.of(2020, 3, 1);
        LocalDate date2 = LocalDate.of(2020, 6, 1);
        LocalDate date3 = LocalDate.of(2021, 4, 1);
        BigDecimal amount1 = BigDecimal.valueOf(0.22);
        BigDecimal amount2 = BigDecimal.valueOf(0.23);
        BigDecimal amount3 = BigDecimal.valueOf(0.32);
        Dividend dividend1 = dividend(date1, amount1);
        Dividend dividend2 = dividend(date2, amount2);
        Dividend dividend3 = dividend(date3, amount3);
        List<Dividend> dividends = new ArrayList<Dividend>();
        dividends.add(dividend1);
        dividends.add(dividend2);
        dividends.add(dividend3);
        List<DividendEntity> dividendEntities = dividends.stream()
                .map(dividendDataAccessMapper::dividendToDividendEntity)
                .toList();
        String name = "Apple";
        String symbol = "AAPL";
        StockEntity stockEntity = StockEntity.builder()
                .name(name)
                .symbol(symbol)
                .dividends(dividendEntities)
                .build();
        stockJpaRepository.save(stockEntity);

        List<AnnualDividend> expected = new ArrayList<AnnualDividend>();
        expected.add(new AnnualDividend(2020, BigDecimal.valueOf(0.45)));
        expected.add(new AnnualDividend(2021, BigDecimal.valueOf(0.32)));


        //when
        List<AnnualDividend> result = getHistory.getAnnualDividendHistoryByName(name);

        //then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    private Dividend dividend(LocalDate date, BigDecimal amount) {
        return Dividend.builder()
                .date(date)
                .amount(amount)
                .build();
    }


}
