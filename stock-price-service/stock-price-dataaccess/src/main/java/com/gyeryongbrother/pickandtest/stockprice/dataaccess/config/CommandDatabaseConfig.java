package com.gyeryongbrother.pickandtest.stockprice.dataaccess.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter.command",
                "com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository"
        },
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager"
)
public class CommandDatabaseConfig {
}
