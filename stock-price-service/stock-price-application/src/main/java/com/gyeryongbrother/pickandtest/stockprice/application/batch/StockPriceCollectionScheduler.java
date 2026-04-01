package com.gyeryongbrother.pickandtest.stockprice.application.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockPriceCollectionScheduler {

    private final JobLauncher jobLauncher;
    private final Job stockPriceCollectionJob;

    @Scheduled(cron = "0 0 18 * * MON-FRI", zone = "Asia/Seoul")
    public void triggerStockPriceCollectionJob() {
        log.info("[Batch Scheduler] Starting stock price collection job...");
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("startTime", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(stockPriceCollectionJob, params);
            log.info("[Batch Scheduler] Stock price collection job launched.");
        } catch (Exception e) {
            log.error("[Batch Scheduler] Failed to launch job", e);
        }
    }
}
