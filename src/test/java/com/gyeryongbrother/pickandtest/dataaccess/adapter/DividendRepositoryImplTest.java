package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestQuerydslConfig.class)
public class DividendRepositoryImplTest {

    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private StockRepository stockRepository;

    @Test
    void save(){
        //given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        Stock stock = Stock.builder()
                .name("name")
                .symbol("symbol")
                .listingDate(januaryFirst)
                .build();
        Stock savedstock=stockRepository.save(stock);
        Long stockId=savedstock.getId();
        BigDecimal amount=BigDecimal.valueOf(100);
        Dividend dividend=Dividend.builder()
                .stockId(stockId)
                .amount(amount)
                .date(januaryFirst)
                .build();
        Dividend expected=Dividend.builder()
                .stockId(stockId)
                .amount(amount)
                .date(januaryFirst)
                .build();

        //when
        Dividend result=dividendRepository.save(dividend);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
