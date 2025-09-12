package com.gyeryongbrother.pickandtest.stockprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.gyeryongbrother.pickandtest.stockprice")
public class StockPriceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockPriceServiceApplication.class, args);
    }
}
