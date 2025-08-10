package com.gyeryongbrother.pickandtest.stockprice.dataaccess.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter.query",
        entityManagerFactoryRef = "slaveEntityManagerFactory",
        transactionManagerRef = "slaveTransactionManager"
)
public class QueryDatabaseConfig {
}
