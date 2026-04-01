package com.gyeryongbrother.pickandtest.stockprice.application.batch;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.LocalStockEntity;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.LocalStockJpaRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.item.support.builder.SynchronizedItemStreamReaderBuilder;

import java.util.Collections;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StockPriceCollectionBatchJob {

    private final LocalStockJpaRepository localStockJpaRepository;
    private final StockPriceFetcher stockPriceFetcher;
    private final StockPriceRepository stockPriceRepository;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job stockPriceCollectionJob(JobRepository jobRepository, Step stockPriceCollectionStep) {
        return new JobBuilder("stockPriceCollectionJob", jobRepository)
                .start(stockPriceCollectionStep)
                .build();
    }

    @Bean
    public Step stockPriceCollectionStep(JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("stockPriceCollectionStep", jobRepository)
                .<LocalStockEntity, Stock>chunk(CHUNK_SIZE, transactionManager)
                .reader(new SynchronizedItemStreamReaderBuilder<LocalStockEntity>()
                        .delegate(localStockReader())
                        .build())
                .processor(stockPriceProcessor())
                .writer(stockPriceWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setThreadNamePrefix("batch-thread-");
        executor.initialize();
        return executor;
    }

    @Bean
    public RepositoryItemReader<LocalStockEntity> localStockReader() {
        return new RepositoryItemReaderBuilder<LocalStockEntity>()
                .name("localStockReader")
                .repository(localStockJpaRepository)
                .methodName("findAll")
                .pageSize(CHUNK_SIZE)
                .sorts(Collections.singletonMap("stockId", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemProcessor<LocalStockEntity, Stock> stockPriceProcessor() {
        return localStock -> new Stock(localStock.getStockId(), localStock.getSymbol());
    }

    @Bean
    public ItemWriter<Stock> stockPriceWriter() {
        return chunk -> {
            List<Stock> stockList = new java.util.ArrayList<>(chunk.getItems());
            if (stockList.isEmpty()) return;

            Stocks stocks = new Stocks(stockList);
            log.info("[Batch] Bulk fetching prices for {} stocks", stockList.size());

            List<StockPrice> allPrices = stockPriceFetcher.fetchStockPrices(stocks, null);
            
            stockPriceRepository.saveAll(allPrices);
            log.info("[Batch] Saved {} stock prices", allPrices.size());
        };
    }
}
