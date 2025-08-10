package com.gyeryongbrother.pickandtest.stock.dataaccess.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.gyeryongbrother.pickandtest.stock.dataaccess.repository",
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager"
)
public class CommandDatabaseConfig {
}
