package com.gyeryongbrother.pickandtest.dividend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gyeryongbrother.pickandtest.dividend")
public class DividendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DividendServiceApplication.class, args);
    }
}
