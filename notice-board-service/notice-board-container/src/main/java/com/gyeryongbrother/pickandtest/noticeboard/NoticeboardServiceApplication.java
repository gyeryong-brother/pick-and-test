package com.gyeryongbrother.pickandtest.noticeboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gyeryongbrother.pickandtest.noticeboard")
public class NoticeboardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeboardServiceApplication.class, args);
    }
}
