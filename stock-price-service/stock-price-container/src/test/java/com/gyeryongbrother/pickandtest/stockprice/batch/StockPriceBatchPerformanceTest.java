package com.gyeryongbrother.pickandtest.stockprice.batch;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.LocalStockEntity;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.LocalStockJpaRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StopWatch;

@Slf4j
@SpringBootTest(properties = {
    "spring.cloud.config.enabled=false",
    "eureka.client.enabled=false",
    "spring.config.import=optional:configserver:",
    "spring.batch.job.enabled=false",
    "spring.jpa.properties.hibernate.jdbc.batch_size=100",
    "spring.jpa.properties.hibernate.order_inserts=true",
    "spring.jpa.properties.hibernate.order_updates=true"
})
@ActiveProfiles("performance-test")
class StockPriceBatchPerformanceTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job stockPriceCollectionJob;

    @Autowired
    private LocalStockJpaRepository localStockJpaRepository;

    @Autowired
    private com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.StockPriceJpaRepository stockPriceJpaRepository;

    private static final int TOTAL_STOCKS = 10_000;

    @BeforeEach
    void setUp() {
        stockPriceJpaRepository.deleteAll();
        localStockJpaRepository.deleteAll();
        
        log.info("Preparing {} local stock entities for performance test...", TOTAL_STOCKS);
        List<LocalStockEntity> dummies = new ArrayList<>();
        for (long i = 1; i <= TOTAL_STOCKS; i++) {
            dummies.add(new LocalStockEntity(i, "SYM" + i));
        }
        localStockJpaRepository.saveAll(dummies);
        log.info("Prepared dummy stocks successfully.");
    }

    @Test
    @DisplayName("[Batch Performance] 1만건의 주가를 Spring Batch (Chunk:100)로 수집하는 성능 지표 도출")
    void measureSpringBatchCollectionThroughput() throws Exception {
        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        StopWatch stopWatch = new StopWatch();

        // when
        log.info("=== Start Batch Processing ===");
        stopWatch.start();
        JobExecution jobExecution = jobLauncher.run(stockPriceCollectionJob, jobParameters);
        stopWatch.stop();
        log.info("=== End Batch Processing ===");

        // then
        assertThat(jobExecution.getStatus().isUnsuccessful()).isFalse();

        long savedPrices = stockPriceJpaRepository.count();
        log.info("Saved prices count: {}", savedPrices);
        
        long timeTakenMs = stopWatch.getTotalTimeMillis();
        assertThat(timeTakenMs).isLessThan(30_000L);
    }
}
